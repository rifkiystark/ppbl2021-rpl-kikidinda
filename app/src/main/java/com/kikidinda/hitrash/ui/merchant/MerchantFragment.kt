package com.kikidinda.hitrash.ui.merchant

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.adapter.MerchantAdapter
import com.kikidinda.hitrash.ui.scanner.ScannerActivity
import kotlinx.android.synthetic.main.fragment_merchant.*


class MerchantFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_merchant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMerchant.adapter = MerchantAdapter()
        btnScan.setOnClickListener {
            startActivity(
                Intent(requireContext(), ScannerActivity::class.java)
            )
        }

    }
}