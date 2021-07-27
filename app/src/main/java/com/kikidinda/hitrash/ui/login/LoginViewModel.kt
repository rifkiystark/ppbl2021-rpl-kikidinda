package com.kikidinda.hitrash.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.local.LocalStorage

class LoginViewModel : ViewModel() {
    private val repo = FirestoreUser()

    val loading = MutableLiveData<Boolean>()

    val message = MutableLiveData<String>()

    private val successLogin = MutableLiveData<User>()

    fun loadingBroadcaster() : LiveData<Boolean> = loading

    fun messageBroadcaster() : LiveData<String> = message

    fun successBroadcaster() : LiveData<User> = successLogin

    fun login(email: String, password: String, context: Context) {
        loading.value = true
        repo.getUserByEmail(email) { _, user ->
            when {
                user == null -> {
                    message.value = "Email salah"
                }
                user.password != password -> {
                    message.value = "Password salah"
                }
                else -> {
                    LocalStorage.setLogin(context, true)
                    LocalStorage.setUser(context, user)
                    successLogin.value = user
                }
            }
        }
    }
}