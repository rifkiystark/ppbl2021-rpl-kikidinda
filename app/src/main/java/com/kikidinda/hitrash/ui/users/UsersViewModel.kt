package com.kikidinda.hitrash.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreAppInfo
import com.kikidinda.hitrash.repository.firestore.FirestoreUser

class UsersViewModel : ViewModel() {
    val users = MutableLiveData<List<User>>()
    fun userObservable(): LiveData<List<User>> = users
    fun getUsers() {
        FirestoreUser.getUser {
            users.value = it
        }
    }

    fun makeMerchant(id: String) {
        FirestoreUser.makeMerchant(id) {
            FirestoreAppInfo.addWarung()
            getUsers()
        }
    }
}