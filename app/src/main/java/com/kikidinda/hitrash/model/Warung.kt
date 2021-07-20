package com.kikidinda.hitrash.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Warung(
    @DocumentId
    var id : String? = "",
    var img: String = "https://firebasestorage.googleapis.com/v0/b/hitrash-ddd0c.appspot.com/o/merchant%2Fmerchant.jpg?alt=media&token=1b21d757-3e1a-481f-829b-9f81b9a2f5cd",
    var merchantName : String = "",
    var merchantType : String = "",
    var merchantGoods : String = ""
) : Parcelable
