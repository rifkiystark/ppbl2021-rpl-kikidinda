package com.kikidinda.hitrash.ui.deposittrash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.Transaction
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreAppInfo
import com.kikidinda.hitrash.repository.firestore.FirestoreUser

class DepositTrashViewModel : ViewModel() {
    val users = MutableLiveData<List<User>>()

    init {
        FirestoreUser.getUser {
            users.value = it
        }
    }

    fun userObserver(): LiveData<List<User>> = users

    fun addPoin(user: User, poin: Int) {
        val transaction = Transaction(poin = poin)
        FirestoreUser.addPoin(user.id!!, poin)
        FirestoreUser.addTransaction(transaction, user.id!!)
        FirestoreAppInfo.addTrash(poin)
    }
}