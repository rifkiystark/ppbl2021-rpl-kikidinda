package com.kikidinda.hitrash.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.repository.local.LocalStorage
import com.kikidinda.hitrash.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val onboarding = listOf(
            OnboardingItem("Dapatkan Poin","Tukarkan sampah dan dapatkan poin", R.drawable.onboarding1),
            OnboardingItem("Hi-Trash Shop","Kamu bisa belanja dengan poin di warung-warung yang sudah menjadi mitra", R.drawable.onboarding2),
            OnboardingItem("Tiga","apakah in", R.drawable.onboarding3)
        )

        btnSkip.setOnClickListener {
            LocalStorage.setFirst(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val adapter = OnboardingAdapter(onboarding)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        indicator1.setImageResource(R.drawable.ic_indicator_selected)
                        indicator2.setImageResource(R.drawable.ic_indicator_unselected)
                        indicator3.setImageResource(R.drawable.ic_indicator_unselected)
                    }
                    1 -> {
                        indicator2.setImageResource(R.drawable.ic_indicator_selected)
                        indicator1.setImageResource(R.drawable.ic_indicator_unselected)
                        indicator3.setImageResource(R.drawable.ic_indicator_unselected)
                    }
                    2 -> {
                        indicator3.setImageResource(R.drawable.ic_indicator_selected)
                        indicator1.setImageResource(R.drawable.ic_indicator_unselected)
                        indicator2.setImageResource(R.drawable.ic_indicator_unselected)
                    }
                }
            }

        })


    }
}