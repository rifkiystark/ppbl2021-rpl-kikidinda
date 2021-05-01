package com.kikidinda.hitrash.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreUser

class LoginViewModel : ViewModel() {
    val loading = MutableLiveData<Boolean>().apply {
        value = false
    }

    val message = MutableLiveData<String>()

    val successLogin = MutableLiveData<User>()

    fun loadingBroadcaster() : LiveData<Boolean> = loading

    fun messageBroadcaster() : LiveData<String> = message

    fun successBroadcaster() : LiveData<User> = successLogin

    fun login(email: String, password: String) {
        loading.value = true
        FirestoreUser.getUserByEmail(email) { msg, user ->
            when {
                user == null -> {
                    message.value = "Email salah"
                }
                user.password != password -> {
                    message.value = "Password salah"
                }
                else -> {
                    successLogin.value = user
                }
            }
        }
    }
}