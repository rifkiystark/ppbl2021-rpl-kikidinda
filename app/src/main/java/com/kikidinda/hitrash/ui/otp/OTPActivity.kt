package com.kikidinda.hitrash.ui.otp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.local.LocalStorage
import com.kikidinda.hitrash.ui.MainActivity
import com.kikidinda.hitrash.utils.Alert
import kotlinx.android.synthetic.main.activity_o_t_p.*

class OTPActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "user"
    }
    lateinit var user : User

    val viewModel : OTPViewModel by viewModels()
    lateinit var alert : SweetAlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_t_p)

        alert = SweetAlertDialog(this)

        user = intent.getParcelableExtra(EXTRA_USER)!!

        setObserver()
        viewModel.requestCode(user.phoneNumber, this)

        btnVerify.setOnClickListener {
            viewModel.verifiyOTPCode(pvOTP.text.toString(), user)
        }
    }

    private fun setObserver() {
        viewModel.loadingBroadcaster().observe(this, Observer {
            if(it){
                alert = Alert.loading(alert)
            }
        })

        viewModel.messageBroadcaster().observe(this, Observer {
            alert = Alert.fail(alert, it)
        })

        viewModel.successRegisterBroadcaster().observe(this, Observer {
            alert = Alert.success(alert, it)
            Handler(mainLooper).postDelayed({
                alert = Alert.hide(alert)
                startActivity(Intent(this, MainActivity::class.java))
                LocalStorage.setLogin(this, true)
                LocalStorage.setUser(this, user)
                finishAffinity()
            }, 1500)

        })
    }
}