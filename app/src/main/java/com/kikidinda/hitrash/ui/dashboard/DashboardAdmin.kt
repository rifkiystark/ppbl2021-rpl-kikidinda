package com.kikidinda.hitrash.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kikidinda.hitrash.R
import kotlinx.android.synthetic.main.fragment_dashboard_admin.*

class DashboardAdmin : Fragment() {


    val viewModel : DashboardViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getInfo().observe(viewLifecycleOwner, Observer {
            totalSampah.text = it.totalSampah.toString()
            totalPengguna.text = it.totalUser.toString()
            totalWarung.text = it.totalMerchant.toString()
            nilaiSampah.text = (it.totalSampah * 1000).toString()
        })
    }
}