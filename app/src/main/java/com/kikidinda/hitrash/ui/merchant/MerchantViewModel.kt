package com.kikidinda.hitrash.ui.merchant

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.Warung
import com.kikidinda.hitrash.repository.firestore.FirestoreUser

class MerchantViewModel : ViewModel() {
    val firestoreUser = FirestoreUser()
    fun getMerchant() : LiveData<ArrayList<Warung>> = firestoreUser.getMerchants()
}