package com.kikidinda.hitrash.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.local.LocalStorage
import com.kikidinda.hitrash.ui.MainActivity
import com.kikidinda.hitrash.ui.MainAdminActivity
import com.kikidinda.hitrash.ui.MainMerchantActivity
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
                val user = LocalStorage.getUser(this)
                FirebaseMessaging.getInstance().token.addOnSuccessListener {
                    FirestoreUser.sendToken(user.id!!, it)
                }
                when {
                    user.admin -> {
                        startActivity(
                            Intent(this, MainAdminActivity::class.java)
                        )
                        finish()
                    }
                    user.warung -> {
                        startActivity(
                            Intent(this, MainMerchantActivity::class.java)
                        )
                        finish()
                    }
                    else -> {
                        startActivity(
                            Intent(this, MainActivity::class.java)
                        )
                        finish()
                    }
                }

            }
        }, 2000)
    }
}