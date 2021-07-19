package com.kikidinda.hitrash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.model.Warung
import kotlinx.android.synthetic.main.item_merchant.view.*

class MerchantAdapter :
    RecyclerView.Adapter<MerchantAdapter.ViewHolder>() {
    var merchants : ArrayList<Warung> = arrayListOf()
    fun updateMerchantData(merchants : ArrayList<Warung>){
        this.merchants = merchants
        notifyDataSetChanged()
    }
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(warung: Warung){
            view.tvTitleMerchant.text = warung.merchantName
            view.tvDetailMerchant.text = warung.merchantType
            view.ivMerchant.load(warung.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_merchant, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return merchants.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(merchants[position])
    }
}