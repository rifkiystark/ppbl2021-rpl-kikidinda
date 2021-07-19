package com.kikidinda.hitrash.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class Transaction(
    @DocumentId
    var id : String? = "",
    var poin : Int = 0,
    var time : Timestamp = Timestamp.now()
)
