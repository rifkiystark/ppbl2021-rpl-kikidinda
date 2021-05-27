package com.kikidinda.hitrash.ui.homeadmin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreUser

class HomeAdminViewModel : ViewModel() {
    val users = MutableLiveData<List<User>>()

    init {
        FirestoreUser.getUser {
            users.value = it
        }
    }

    fun userObserver(): LiveData<List<User>> = users

    fun addPoin(user: User, poin: Int) {
        FirestoreUser.addPoin(user.id!!, poin)
    }
}