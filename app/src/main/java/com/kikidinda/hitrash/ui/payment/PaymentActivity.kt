package com.kikidinda.hitrash.ui.payment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import coil.load
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.model.Warung
import com.kikidinda.hitrash.utils.Alert
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MERCHANT = "extra_merchant"
        const val MSG_NO_POIN = "Poin anda tidak cukup"
        const val MSG_FAILED = "Transaksi tidak bisa dilakukan, coba lagi nanti"
        const val MSG_SUCCESS = "Pembayaran berhasil!"
    }

    val viewModel: PaymentViewModel by viewModels()

    lateinit var alert: SweetAlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        alert = SweetAlertDialog(this)

        val merchant = intent.getParcelableExtra<Warung>(EXTRA_MERCHANT)!!

        setView(merchant)

        setOnClickListener(merchant)

        setObserver()
    }

    private fun setObserver() {
        viewModel.messageObservable().observe(this, {
            alert = if (it == MSG_SUCCESS) {
                Alert.success(alert, it)
            } else {
                Alert.fail(alert, it)
            }
        })
    }

    private fun setOnClickListener(merchant: Warung) {
        btnPay.setOnClickListener {
            val poin = tilPoin.editText?.text.toString().toInt()
            viewModel.pay(poin, merchant.id!!, this)
        }
    }

    private fun setView(merchant: Warung) {
        ivMerchant.load(merchant.img)
        tvMerchantName.text = merchant.merchantName
        tvMerchantType.text = merchant.merchantType
        tvMerchantGoods.text = merchant.merchantGoods
    }
}