package com.kikidinda.hitrash.ui.users

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.adapter.UsersAdapter
import com.kikidinda.hitrash.utils.Alert
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {

    val viewModel: UsersViewModel by viewModels()
    lateinit var adapter: UsersAdapter
    lateinit var alert: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        alert = SweetAlertDialog(this)
        adapter = UsersAdapter { id ->
            alert = Alert.popup(alert){
                Log.d("TAG", "onCreate: $it")
                if(it){
                    viewModel.makeMerchant(id)
                } else {

                }

                alert = Alert.hide(alert)
            }
        }

        viewModel.getUsers()

        setObserver()
        rvUsers.adapter = adapter
    }

    private fun setObserver() {
        viewModel.userObservable().observe(this, {
            adapter.updateUsersData(it)
        })
    }
}