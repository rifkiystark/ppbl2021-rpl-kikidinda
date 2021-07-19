package com.kikidinda.hitrash.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.Transaction
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.local.LocalStorage

class HomeViewModel : ViewModel() {
    fun transaction(context: Context): LiveData<List<Transaction>> =
        FirestoreUser.getTransactions(LocalStorage.getUser(context).id!!)

    fun profile(context: Context): LiveData<User> =
        FirestoreUser.getUserById(LocalStorage.getUser(context).id!!)
}