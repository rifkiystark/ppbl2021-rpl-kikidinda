package com.kikidinda.hitrash.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.ui.MainActivity
import com.kikidinda.hitrash.ui.MainAdminActivity
import com.kikidinda.hitrash.ui.MainMerchantActivity
import com.kikidinda.hitrash.ui.register.RegisterActivity
import com.kikidinda.hitrash.utils.Alert
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val viewModel : LoginViewModel by viewModels()
    lateinit var alert : SweetAlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        alert = SweetAlertDialog(this)

        btnRegister.setOnClickListener {
            startActivity(
                Intent(this, RegisterActivity::class.java)
            )
        }

        btnMasuk.setOnClickListener {
            viewModel.login(etEmail.text.toString(), etPassword.text.toString(), this)
        }

        setObserver()
    }

    private fun setObserver() {
        viewModel.loadingBroadcaster().observe(this, Observer {
            if(it)
                alert = Alert.loading(alert)
        })

        viewModel.messageBroadcaster().observe(this, Observer {
            alert = Alert.fail(alert, it)
        })

        viewModel.successBroadcaster().observe(this, Observer {
            alert = Alert.hide(alert)
            when {
                it.admin -> {
                    startActivity(
                        Intent(this, MainAdminActivity::class.java)
                    )
                    finish()
                }
                it.warung -> {
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
            finishAffinity()
        })
    }
}