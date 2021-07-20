package com.kikidinda.hitrash.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment(val mode: Int) : Fragment() {

    val viewModel: HistoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HistoryAdapter()
        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        rvHistory.adapter = adapter

        viewModel.getTransactions(mode, requireContext()).observe(viewLifecycleOwner, {
            adapter.updateTransactionData(it)
        })
    }

}