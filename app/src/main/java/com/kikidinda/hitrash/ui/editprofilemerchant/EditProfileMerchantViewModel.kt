package com.kikidinda.hitrash.ui.editprofilemerchant

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.Warung
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.local.LocalStorage
import com.kikidinda.hitrash.repository.storage.Storage

class EditProfileMerchantViewModel : ViewModel() {

    fun getMerchant(context: Context) {
        val user = LocalStorage.getUser(context)
        FirestoreUser.getMerchant(user.id!!) { status, merchant ->
            this.merchant = merchant
            merchantObservable.value = this.merchant
            Log.d("TAG", "getMerchant: $status, $merchant")
        }
    }


    val isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }

    var merchant: Warung? = null
    val merchantObservable = MutableLiveData<Warung?>().apply {
        value = merchant
    }

    fun uploadMerchantImage(uri: Uri, context: Context) {
        isLoading.value = true
        val user = LocalStorage.getUser(context)

        Storage.upload(uri, user) { status, url ->
            isLoading.value = false
            if (status) {
                if (merchant == null) {
                    merchant = Warung(img = url)
                    merchantObservable.value = merchant
                    FirestoreUser.updateMerchant(merchant!!, user.id!!) { status, merchant ->

                    }
                }
            }

        }
    }

    fun updateMerchant(
        merchantName: String,
        merchantType: String,
        merchantGoods: String,
        context: Context
    ) {
        isLoading.value = true
        val user = LocalStorage.getUser(context)
        if (merchant == null) {
            merchant = Warung(merchantName = merchantName, merchantType = merchantType, merchantGoods = merchantGoods)
        } else {
            merchant!!.merchantName = merchantName
            merchant!!.merchantGoods = merchantGoods
            merchant!!.merchantType = merchantType
        }
        merchantObservable.value = merchant
        FirestoreUser.updateMerchant(merchant!!, user.id!!) { status, merchant ->
            isLoading.value = false
        }
    }
}