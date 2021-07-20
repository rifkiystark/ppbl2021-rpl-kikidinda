package com.kikidinda.hitrash.ui.payment

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.Transaction
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.local.LocalStorage

class PaymentViewModel : ViewModel() {

    private val message = MutableLiveData<String>()

    fun messageObservable(): LiveData<String> = message

    fun pay(poin: Int, id: String, context: Context) {
        val user = LocalStorage.getUser(context)
        FirestoreUser.getUserByEmail(user.email) { msg, myUser ->
            Log.d("TAG", "pay: $myUser")
            if (myUser == null) {
                message.value = PaymentActivity.MSG_FAILED
            } else {
                if (myUser.poin < poin) {
                    message.value = PaymentActivity.MSG_NO_POIN
                } else {
                    FirestoreUser.addPoin(id, poin)
                    FirestoreUser.subtractPoin(user.id!!, poin)
                    val transactionIn = Transaction(poin = poin)
                    val transactionOut = Transaction(poin = (poin * -1))
                    FirestoreUser.addTransaction(transactionIn, id)
                    FirestoreUser.addTransaction(transactionOut, user.id!!)
                    message.value = PaymentActivity.MSG_SUCCESS
                }
            }
        }


    }
}