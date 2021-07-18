package com.kikidinda.hitrash.ui.qrcode

import android.R.attr.bitmap
import android.os.Bundle
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.WriterException
import com.kikidinda.hitrash.R
import kotlinx.android.synthetic.main.activity_qrcode.*


class QRCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)

        val qrgEncoder = QRGEncoder("testes", null, QRGContents.Type.TEXT, 300)

        try {
            // Getting QR-Code as Bitmap
            val bitmap = qrgEncoder.bitmap
            // Setting Bitmap to ImageView
            qrCode.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            Log.d("TAG", e.toString())
        }
    }
}