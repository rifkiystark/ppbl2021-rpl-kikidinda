package com.kikidinda.hitrash.ui.history

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.Transaction
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.local.LocalStorage

class HistoryViewModel : ViewModel() {
    val firestoreUser = FirestoreUser()
    fun getTransactions(mode: Int, context: Context): LiveData<List<Transaction>> {
        val user = LocalStorage.getUser(context)
        return if (mode == -1) {
            firestoreUser.getTransactionsOut(user.id!!)
        } else {
            firestoreUser.getTransactionsIn(user.id!!)
        }
    }
}