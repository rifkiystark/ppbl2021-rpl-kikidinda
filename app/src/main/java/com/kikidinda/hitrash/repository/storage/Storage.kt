package com.kikidinda.hitrash.repository.storage

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.kikidinda.hitrash.model.User

object Storage {
    private const val rootDir = "/merchant"

    val instance = FirebaseStorage.getInstance()

    fun upload(image: Uri, user: User, callback: (Boolean, String) -> Unit) {
        var reference = instance.reference.child(rootDir)
        val merchantImage = reference.child("${user.id}.png")
        val uploadTask = merchantImage.putFile(image)
        val url = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            merchantImage.downloadUrl

        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                callback(true, downloadUri.toString())
                Log.d("TAG", "upload: $downloadUri")
            } else {
                callback(false, "none")
                Log.d("TAG", "upload: Gagal")
            }
        }

    }
}