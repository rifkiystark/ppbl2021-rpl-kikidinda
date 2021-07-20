package com.kikidinda.hitrash.repository.firestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FieldValue
import com.kikidinda.hitrash.model.AppInfo
import com.kikidinda.hitrash.utils.CONST

object FirestoreAppInfo : FirestoreIntance() {
    fun getAppInfo() : LiveData<AppInfo> {
        val appInfoLiveData = MutableLiveData<AppInfo>()
        db.collection(CONST.FIRESTORE.APP_INFO).document(CONST.FIRESTORE.APP_INFO).addSnapshotListener { value, error ->
            if (!value?.exists()!!) {
                appInfoLiveData.postValue(AppInfo())
            } else {
                val appinfo = value?.toObject(AppInfo::class.java)
                appInfoLiveData.postValue(appinfo!!)
            }
        }

        return appInfoLiveData
    }


    fun addUser() {
        db.collection(CONST.FIRESTORE.APP_INFO).document(CONST.FIRESTORE.APP_INFO)
            .update("totalUser", FieldValue.increment(1.toLong()))
    }

    fun addWarung() {
        db.collection(CONST.FIRESTORE.APP_INFO).document(CONST.FIRESTORE.APP_INFO)
            .update("totalMerchant", FieldValue.increment(1.toLong()))
    }

    fun addTrash(trash : Int) {
        db.collection(CONST.FIRESTORE.APP_INFO).document(CONST.FIRESTORE.APP_INFO)
            .update("totalSampah", FieldValue.increment(trash.toLong()))
    }

}