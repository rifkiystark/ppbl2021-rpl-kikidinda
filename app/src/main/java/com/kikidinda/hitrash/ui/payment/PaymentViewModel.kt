package com.kikidinda.hitrash.ui.payment

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.Notification
import com.kikidinda.hitrash.model.NotificationResponse
import com.kikidinda.hitrash.model.Transaction
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.local.LocalStorage
import com.kikidinda.hitrash.repository.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentViewModel : ViewModel() {

    val firestoreUser = FirestoreUser()
    private val message = MutableLiveData<String>()

    fun messageObservable(): LiveData<String> = message

    fun pay(poin: Int, id: String, context: Context) {
        val user = LocalStorage.getUser(context)
        firestoreUser.getUserByEmail(user.email) { msg, myUser ->
            Log.d("TAG", "pay: $myUser")
            if (myUser == null) {
                message.value = PaymentActivity.MSG_FAILED
            } else {
                if (myUser.poin < poin) {
                    message.value = PaymentActivity.MSG_NO_POIN
                } else {
                    firestoreUser.addPoin(id, poin)
                    firestoreUser.subtractPoin(user.id!!, poin)
                    val transactionIn = Transaction(poin = poin)
                    val transactionOut = Transaction(poin = (poin * -1))
                    firestoreUser.addTransaction(transactionIn, id)
                    firestoreUser.addTransaction(transactionOut, user.id!!)
                    message.value = PaymentActivity.MSG_SUCCESS

                    firestoreUser.getUserByIdCallback(id) { msg, user2 ->
                        if(user2 != null){
                            if(user2.token != ""){
                                val notification = Notification(
                                    to = user2.token,
                                    data = Notification.Data(
                                        message = "Anda mendapatkan poin sebesar $poin"
                                    )
                                )
                                RetrofitInstance.service().send(notification).enqueue(object :
                                    Callback<NotificationResponse> {
                                    override fun onResponse(
                                        call: Call<NotificationResponse>,
                                        response: Response<NotificationResponse>
                                    ) {

                                    }

                                    override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {

                                    }

                                })

                            }
                        }

                    }
                }
            }
        }


    }
}