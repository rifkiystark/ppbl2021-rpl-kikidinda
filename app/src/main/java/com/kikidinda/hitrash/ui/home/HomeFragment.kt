package com.kikidinda.hitrash.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.adapter.HistoryAdapter
import com.kikidinda.hitrash.model.Transaction
import com.kikidinda.hitrash.ui.history.HistoryActivity
import com.kikidinda.hitrash.ui.howto.HowToActivity
import com.kikidinda.hitrash.ui.profile.ProfileViewModel
import com.kikidinda.hitrash.utils.Helper
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    val viewModel : HomeViewModel by viewModels()
    lateinit var adapter : HistoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        adapter = HistoryAdapter()
        rvHistory.adapter = adapter

        btnTukarSampah.setOnClickListener {
            requireContext().startActivity(
                Intent(requireContext(), HowToActivity::class.java)
            )
        }
        btnTukarCoin.setOnClickListener {
            requireContext().startActivity(
                Intent(requireContext(), HowToActivity::class.java)
            )
        }

        btnMore.setOnClickListener {
            requireContext().startActivity(
                Intent(requireContext(), HistoryActivity::class.java)
            )
        }

        viewModel.profile(requireContext()).observe(viewLifecycleOwner, {
            tvGreeting.text = "Halo, " + Helper.getSimpleName(it.name)
            tvCoin.text = it.poin.toString()
        })

        viewModel.transaction(requireContext()).observe(viewLifecycleOwner, {
            adapter.updateTransactionData(it)
        })
    }
}