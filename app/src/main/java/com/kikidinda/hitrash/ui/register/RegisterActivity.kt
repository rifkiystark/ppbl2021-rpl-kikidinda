package com.kikidinda.hitrash.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.ui.otp.OTPActivity
import com.kikidinda.hitrash.utils.Alert
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModels()
    lateinit var alert: SweetAlertDialog
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        alert = SweetAlertDialog(this)

        setObserver()

        setOnClick()
    }

    private fun setObserver() {
        viewModel.loadingBroadcaster().observe(this, Observer {
            if (it) {
                alert = Alert.loading(alert)
            }
        })

        viewModel.successRegisterBroadcaster().observe(this, Observer {
            if (it) {
                alert = Alert.hide(alert)
                startActivity(
                    Intent(
                        this,
                        OTPActivity::class.java
                    ).putExtra(OTPActivity.EXTRA_USER, user)
                )
            }
        })

        viewModel.messageBroadcaster().observe(this, Observer {
            alert = Alert.fail(alert, it)
        })
    }

    private fun setOnClick() {
        btnMasuk.setOnClickListener {
            finish()
        }

        btnRegister.setOnClickListener {
            user = User(
                email = etEmail.text.toString(),
                password = etPassword.text.toString(),
                name = etName.text.toString(),
                phoneNumber = etPhoneNumber.text.toString()
            )
            viewModel.register(
                etPhoneNumber.text.toString(),
                etEmail.text.toString()
            )
        }
    }
}