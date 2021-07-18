package com.kikidinda.hitrash.ui.qrcode

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.google.zxing.WriterException
import com.kikidinda.hitrash.R
import kotlinx.android.synthetic.main.fragment_q_r_code.*


class QRCodeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_q_r_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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