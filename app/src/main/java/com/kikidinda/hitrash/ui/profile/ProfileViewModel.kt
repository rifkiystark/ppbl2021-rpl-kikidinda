package com.kikidinda.hitrash.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.local.LocalStorage

class ProfileViewModel : ViewModel() {
    val profile = MutableLiveData<User>()

    fun profileBroadcaster() : LiveData<User> = profile

    fun getProfile(context: Context) {
        FirestoreUser.getUserById(LocalStorage.getUser(context).id!!){ message, user ->
            profile.value = user
        }
    }
}