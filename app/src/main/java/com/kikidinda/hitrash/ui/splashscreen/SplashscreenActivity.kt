package com.kikidinda.hitrash.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.ui.login.LoginActivity

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler(mainLooper).postDelayed({
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }, 2000)
    }
}