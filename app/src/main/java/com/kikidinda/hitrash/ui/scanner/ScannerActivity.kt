package com.kikidinda.hitrash.ui.scanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.budiyev.android.codescanner.*
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.ui.payment.PaymentActivity
import com.kikidinda.hitrash.utils.Alert
import kotlinx.android.synthetic.main.activity_scanner.*

class ScannerActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner

    val viewModel: ScannerViewModel by viewModels()
    lateinit var alert : SweetAlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        alert = SweetAlertDialog(this)
        initQRScanner()

        setObserver()
    }

    private fun setObserver() {
        viewModel.isLoading().observe(this, {
            if(it){
                alert = Alert.loading(alert)
            }
        })

        viewModel.merchantObservable().observe(this, {
            if(it == null){
                alert = Alert.fail(alert, "Warung tidak ditemukan")
            } else {
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putExtra(PaymentActivity.EXTRA_MERCHANT, it)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun initQRScanner() {
        codeScanner = CodeScanner(this, codeScannerViewScannerQRCode)

        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS

        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Log.d("TAG", "initQRScanner: ${it.text}")
                viewModel.getMerchant(it.text)
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(
                    this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        codeScannerViewScannerQRCode.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}