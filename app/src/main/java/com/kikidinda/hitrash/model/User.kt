package com.kikidinda.hitrash.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @DocumentId
    var id : String? = "",
    val name : String = "",
    val email : String = "",
    val password : String = "",
    val phoneNumber : String = "",
    val poin : Int = 0,
    val admin: Boolean = false
) : Parcelable