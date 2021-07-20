package com.kikidinda.hitrash.ui.qrcode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.fragment.app.Fragment
import com.google.zxing.WriterException
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.repository.local.LocalStorage
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

        val id = LocalStorage.getUser(requireContext()).id!!

        val qrgEncoder = QRGEncoder(id, null, QRGContents.Type.TEXT, 300)

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