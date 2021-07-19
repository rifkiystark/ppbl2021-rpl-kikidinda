package com.kikidinda.hitrash.ui.merchant

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.adapter.MerchantAdapter
import com.kikidinda.hitrash.ui.scanner.ScannerActivity
import kotlinx.android.synthetic.main.fragment_merchant.*


class MerchantFragment : Fragment() {

    val viewModel : MerchantViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_merchant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MerchantAdapter()

        rvMerchant.adapter = adapter
        viewModel.getMerchant().observe(viewLifecycleOwner, {
            adapter.updateMerchantData(it)
        })
        btnScan.setOnClickListener {
            startActivity(
                Intent(requireContext(), ScannerActivity::class.java)
            )
        }

    }
}