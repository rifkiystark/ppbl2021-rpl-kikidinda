package com.kikidinda.hitrash.ui.howto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.adapter.HowToUseAdapter
import com.kikidinda.hitrash.model.HowToUse
import kotlinx.android.synthetic.main.activity_how_to.*

class HowToActivity : AppCompatActivity() {
    var listHowToUse : ArrayList<HowToUse> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to)

        rvHowToUse.layoutManager = LinearLayoutManager(this)

        listHowToUse.add(HowToUse("Jadi gini", "Ini deksripsi"))
        listHowToUse.add(HowToUse("Jadi gini", "Ini deksripsi", R.drawable.ic_header_home))
        listHowToUse.add(HowToUse("Jadi gini", "Ini deksripsi", R.drawable.ic_header_home))
        listHowToUse.add(HowToUse("Jadi gini", "Ini deksripsi"))

        rvHowToUse.adapter = HowToUseAdapter(listHowToUse)

        btnBack.setOnClickListener {
            finish()
        }
    }
}