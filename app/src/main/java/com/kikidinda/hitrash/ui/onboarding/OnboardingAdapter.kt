package com.kikidinda.hitrash.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kikidinda.hitrash.R
import kotlinx.android.synthetic.main.onboarding_item_layout.view.*

class OnboardingAdapter(val onboardingList : List<OnboardingItem>) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(onboardingItem: OnboardingItem) {
            view.textTitle.text = onboardingItem.title
            view.textDescription.text = onboardingItem.subtitle
            view.imageSlideIcon.setImageResource(onboardingItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(onboardingList[position])
    }

    override fun getItemCount(): Int = onboardingList.size

}