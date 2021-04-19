package com.kikidinda.hitrash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.model.HowToUse
import kotlinx.android.synthetic.main.item_howto.view.*

class HowToUseAdapter(val listHowToUse:ArrayList<HowToUse>) : RecyclerView.Adapter<HowToUseAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_howto, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listHowToUse.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listHowToUse[position], position)
        if(position == listHowToUse.size - 1)
            holder.itemView.garis.visibility = View.GONE

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(itemHowToUse : HowToUse, position: Int){
            with(itemView){
                tvHowToUseTitle.text = itemHowToUse.title
                tvHowToUseDescription.text = itemHowToUse.description
                if(itemHowToUse.image != null){
                    ivHowToUseImage.visibility = View.VISIBLE
                    // trus set gambarnya
                }

                tvHowToUseNumber.text = (position+1).toString()
            }
        }
    }

}