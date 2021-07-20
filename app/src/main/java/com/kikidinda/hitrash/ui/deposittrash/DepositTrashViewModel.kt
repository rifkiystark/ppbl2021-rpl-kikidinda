package com.kikidinda.hitrash.ui.deposittrash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.Notification
import com.kikidinda.hitrash.model.NotificationResponse
import com.kikidinda.hitrash.model.Transaction
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreAppInfo
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        FirestoreUser.getUserByEmail(user.email) { msg, user2 ->
            if (user2 != null) {
                if (user2.token != "") {
                    val notification = Notification(
                        to = user2.token,
                        data = Notification.Data(
                            message = "Anda mendapatkan poin sebesar $poin"
                        )
                    )
                    RetrofitInstance.service().send(notification)
                        .enqueue(object : Callback<NotificationResponse> {
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