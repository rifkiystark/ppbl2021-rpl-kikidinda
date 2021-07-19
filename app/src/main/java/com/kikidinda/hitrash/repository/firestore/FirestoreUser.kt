package com.kikidinda.hitrash.repository.firestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.kikidinda.hitrash.model.Transaction
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.model.Warung
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

    fun getUser(onResult: (List<User>) -> Unit) {
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

    fun getUserById(id: String): LiveData<User> {
        val userLiveData = MutableLiveData<User>()
        db.collection(CONST.FIRESTORE.USER).document(id).addSnapshotListener { value, _ ->
            if (value != null) {
                if (value.exists())
                    userLiveData.postValue(value.toObject(User::class.java))
            }
        }

        return userLiveData
    }

    fun registerNewUser(id: String, user: User, onResult: (String?, Boolean) -> Unit) {
        db.collection(CONST.FIRESTORE.USER).document(id).set(user)
            .addOnSuccessListener {
                onResult("Register User Berhasil", true)
                FirestoreAppInfo.addUser()
            }
            .addOnFailureListener {
                onResult(it.localizedMessage, false)
            }
    }

    fun addPoin(id: String, poin: Int) {
        db.collection(CONST.FIRESTORE.USER).document(id)
            .update("poin", FieldValue.increment(poin.toLong()))
    }

    fun makeMerchant(id: String, callback: (Boolean) -> Unit) {
        db.collection(CONST.FIRESTORE.USER).document(id).update("warung", true)
            .addOnSuccessListener {
                callback(true)
            }
    }

    fun updateMerchant(merchant: Warung, id: String, callback: (Boolean, Warung) -> Unit) {
        db.collection(CONST.FIRESTORE.USER).document(id).collection(CONST.FIRESTORE.USER_MERCHANT)
            .document(id).set(
                merchant, SetOptions.merge()
            ).addOnSuccessListener {
                callback(true, merchant)
            }
            .addOnFailureListener {
                callback(true, merchant)
            }
    }

    fun getMerchant(id: String, callback: (Boolean, Warung?) -> Unit) {
        db.collection(CONST.FIRESTORE.USER).document(id).collection(CONST.FIRESTORE.USER_MERCHANT)
            .document(id).get().addOnSuccessListener {
                val merchant = it.toObject(Warung::class.java)
                if (merchant == null) {
                    callback(false, null)
                } else {
                    callback(true, merchant)
                }
            }.addOnFailureListener {
                callback(false, null)
            }
    }

    fun getMerchants(): LiveData<ArrayList<Warung>> {
        val warungLiveData = MutableLiveData<ArrayList<Warung>>()
        val warungs = arrayListOf<Warung>()
        db.collection(CONST.FIRESTORE.USER).whereEqualTo("warung", true).get()
            .addOnSuccessListener {
                it.documents.forEach { doc ->
                    doc.reference.collection(CONST.FIRESTORE.USER_MERCHANT).document(doc.id).get()
                        .addOnSuccessListener { warungRes ->
                            val warung = warungRes.toObject(Warung::class.java)
                            if (warung != null) {
                                warungs.add(warung)
                                warungLiveData.postValue(warungs)
                            }
                        }
                }
            }.addOnFailureListener {

            }

        return warungLiveData
    }

    fun getTransactions(id: String): LiveData<List<Transaction>> {
        val transactionsLiveData = MutableLiveData<List<Transaction>>()
        db.collection(CONST.FIRESTORE.USER).document(id)
            .collection(CONST.FIRESTORE.USER_TRANSACTIONS)
            .orderBy("time", Query.Direction.DESCENDING)
            .limit(5L)
            .addSnapshotListener { value, _ ->
                value?.toObjects(Transaction::class.java)
                    ?.let { transactionsLiveData.postValue(it) }
            }

        return transactionsLiveData
    }

}