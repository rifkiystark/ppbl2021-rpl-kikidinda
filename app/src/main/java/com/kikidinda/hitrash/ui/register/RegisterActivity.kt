package com.kikidinda.hitrash.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kikidinda.hitrash.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnMasuk.setOnClickListener{
            finish()
        }
    }
}