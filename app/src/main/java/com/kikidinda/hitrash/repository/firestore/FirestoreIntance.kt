package com.kikidinda.hitrash.repository.firestore

import com.google.firebase.firestore.FirebaseFirestore

open class FirestoreIntance {
    val db = FirebaseFirestore.getInstance()
}