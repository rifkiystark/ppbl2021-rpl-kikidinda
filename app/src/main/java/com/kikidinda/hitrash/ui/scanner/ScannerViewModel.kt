package com.kikidinda.hitrash.ui.scanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.Warung
import com.kikidinda.hitrash.repository.firestore.FirestoreUser

class ScannerViewModel : ViewModel() {

    private val loading = MutableLiveData<Boolean>()

    fun isLoading() : LiveData<Boolean> = loading

    private val merchant = MutableLiveData<Warung?>()

    fun merchantObservable() : LiveData<Warung?> = merchant

    fun getMerchant(id: String) {
        loading.value = true
        if(id.contains("/")){
            this.merchant.value = null
        } else {
            FirestoreUser.getMerchantById(id) { status, merchant ->
                this.merchant.value = merchant
            }
        }

    }
}
