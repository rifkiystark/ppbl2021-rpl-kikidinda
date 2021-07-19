package com.kikidinda.hitrash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.model.Transaction
import kotlinx.android.synthetic.main.item_riwayat.view.*
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private var transactions = listOf<Transaction>()

    fun updateTransactionData(transactions : List<Transaction>){
        this.transactions = transactions
        notifyDataSetChanged()
    }
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(transaction: Transaction) {
            if(transaction.poin < 0) {
                view.tvMessage.text = "Pengurangan Poin"
                view.tvPoin.text = transaction.poin.toString()
            } else {
                view.tvPoin.text = "+${transaction.poin}"
                view.tvMessage.text = "Penambahan Poin"
            }
            val date = SimpleDateFormat(
                "d MMMM yyyy",
                Locale("id", "ID")
            ).format(transaction.time.toDate())
            view.tvDate.text = date.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(transactions[position])
    }
}