package com.kikidinda.hitrash.repository.firestore

import com.google.firebase.firestore.FieldValue
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.utils.CONST

object FirestoreUser : FirestoreIntance() {
    fun getUserByPhoneNumber(phoneNumber: String, onResult: (String?, User?) -> Unit) {
        db.collection(CONST.FIRESTORE.USER).whereEqualTo("phoneNumber", phoneNumber).get()
            .addOnSuccessListener {
                if (it.isEmpty) {
                    onResult("Nomor Telepon Belum Terdaftar", null)
                } else {
                    val user = it.documents[0].toObject(User::class.java)
                    onResult("Nomor Telepon Sudah Terdaftar", user)
                }
            }
            .addOnFailureListener {
                onResult(it.localizedMessage, null)
            }
    }

    fun getUser( onResult: (List<User>)->Unit) {
        db.collection(CONST.FIRESTORE.USER).whereEqualTo("admin", false).get()
            .addOnSuccessListener {
                if (it.isEmpty) {
                    onResult(listOf())
                } else {
                    val users = it.toObjects(User::class.java)
                    onResult(users)
                }
            }
            .addOnFailureListener {
                onResult(listOf())
            }
    }

    fun getUserByEmail(email: String, onResult: (String?, User?) -> Unit) {
        db.collection(CONST.FIRESTORE.USER).whereEqualTo("email", email).get()
            .addOnSuccessListener {
                if (it.isEmpty) {
                    onResult("Email Belum Terdaftar", null)
                } else {
                    val user = it.documents[0].toObject(User::class.java)
                    onResult("Email Sudah Terdaftar", user)
                }
            }
            .addOnFailureListener {
                onResult(it.localizedMessage, null)
            }
    }

    fun getUserById(id: String, onResult: (String?, User?) -> Unit) {
        db.collection(CONST.FIRESTORE.USER).document(id).get()
            .addOnSuccessListener {
                if (it == null) {
                    onResult("User Belum Terdaftar", null)
                } else {
                    val user = it.toObject(User::class.java)
                    onResult("User Sudah Terdaftar", user)
                }
            }
            .addOnFailureListener {
                onResult(it.localizedMessage, null)
            }
    }

    fun registerNewUser(id: String, user: User, onResult: (String?, Boolean) -> Unit) {
        db.collection(CONST.FIRESTORE.USER).document(id).set(user)
            .addOnSuccessListener {
                onResult("Register User Berhasil", true)
            }
            .addOnFailureListener {
                onResult(it.localizedMessage, false)
            }
    }

    fun addPoin(id: String, poin: Int) {
        db.collection(CONST.FIRESTORE.USER).document(id)
            .update("poin", FieldValue.increment(poin.toLong()))
    }
}