package com.kikidinda.hitrash.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.Notification
import com.kikidinda.hitrash.model.NotificationResponse
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreAppInfo
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            FirestoreUser.getUserByIdCallback(id) { msg, user2 ->
                if (user2 != null) {
                    if (user2.token != "") {
                        val notification = Notification(
                            to = user2.token,
                            data = Notification.Data(
                                message = "Profile anda sekarang menjadi warung"
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
}