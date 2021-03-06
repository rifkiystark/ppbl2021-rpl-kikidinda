package com.kikidinda.hitrash.repository.local

import android.content.Context
import com.kikidinda.hitrash.model.User
import com.orhanobut.hawk.Hawk

object LocalStorage {

    const val ISLOGIN = "islogin"
    const val USER = "uid"
    const val IS_FIRST = "isfirst"
    private fun init(context: Context) {
        Hawk.init(context).build()
    }

    fun isFirst(context: Context): Boolean {
        init(context)
        return Hawk.get(IS_FIRST, true)
    }

    fun setFirst(context: Context) {
        init(context)
        Hawk.put(IS_FIRST, false)
    }

    fun setLogin(context: Context, state: Boolean) {
        init(context)
        Hawk.put(ISLOGIN, state)
    }

    fun isLogin(context: Context): Boolean {
        init(context)
        return Hawk.get(ISLOGIN, false)
    }

    fun setUser(context: Context, uid: User) {
        init(context)
        Hawk.put(USER, uid)
    }

    fun getUser(context: Context): User {
        init(context)
        return Hawk.get(USER)
    }

    fun signOut(context: Context) {
        init(context)
        Hawk.deleteAll()
    }
}