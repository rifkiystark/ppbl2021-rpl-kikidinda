package com.kikidinda.hitrash.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @DocumentId
    val id : String? = "",
    val name : String = "",
    val email : String = "",
    val password : String = "",
    val phoneNumber : String = ""
) : Parcelable