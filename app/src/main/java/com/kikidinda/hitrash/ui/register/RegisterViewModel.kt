package com.kikidinda.hitrash.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.repository.firestore.FirestoreUser

class RegisterViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>().apply {
        value = false
    }

    val successRegister = MutableLiveData<Boolean>().apply {
        value = false
    }

    val message = MutableLiveData<String>()

    fun loadingBroadcaster(): LiveData<Boolean> = loading

    fun messageBroadcaster(): LiveData<String> = message

    fun successRegisterBroadcaster(): LiveData<Boolean> = successRegister

    fun register(phoneNumber: String, email: String) {
        loading.value = true
        FirestoreUser.getUserByPhoneNumber(phoneNumber) { message, result ->
            if (result == null && message == "Nomor Telepon Belum Terdaftar") {
                FirestoreUser.getUserByEmail(email) { message2, result2 ->
                    if (result2 == null && message2 == "Email Belum Terdaftar") {
                        successRegister.value = true
                    } else {
                        loading.value = false
                        this.message.value = "Email sudah digunakan"
                    }
                }
            } else {
                loading.value = false
                this.message.value = "Nomor telepon sudah digunakan"
            }
        }
    }
}