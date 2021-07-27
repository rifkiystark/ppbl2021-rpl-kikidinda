package com.kikidinda.hitrash.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikidinda.hitrash.model.AppInfo
import com.kikidinda.hitrash.repository.firestore.FirestoreAppInfo

class DashboardViewModel : ViewModel() {
    val firestoreAppInfo = FirestoreAppInfo()
    fun getInfo() : LiveData<AppInfo> = firestoreAppInfo.getAppInfo()
}