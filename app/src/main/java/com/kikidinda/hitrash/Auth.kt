package com.kikidinda.hitrash

import com.google.firebase.auth.FirebaseAuth

object Auth {
    val instance = FirebaseAuth.getInstance()
    val isLogin = instance.uid
}