package com.kikidinda.hitrash.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.repository.local.LocalStorage
import com.kikidinda.hitrash.ui.MainActivity
import com.kikidinda.hitrash.ui.MainAdminActivity
import com.kikidinda.hitrash.ui.login.LoginActivity
import com.kikidinda.hitrash.ui.onboarding.OnboardingActivity

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler(mainLooper).postDelayed({
            if(!LocalStorage.isLogin(this)){
                if(LocalStorage.isFirst(this)){
                    startActivity(
                        Intent(this, OnboardingActivity::class.java)
                    )
                } else {
                    startActivity(
                        Intent(this, LoginActivity::class.java)
                    )
                }

                finish()
            }

            else {
                val admin = LocalStorage.getUser(this).admin
                if(admin){
                    startActivity(
                        Intent(this, MainAdminActivity::class.java)
                    )
                    finish()
                } else {
                    startActivity(
                        Intent(this, MainActivity::class.java)
                    )
                    finish()
                }

            }
        }, 2000)
    }
}