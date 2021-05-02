package com.kikidinda.hitrash.utils

import java.util.*

object Helper {
    fun getSimpleName(name : String) : String {
        val splitedName = name.split(" ")
        val simpleName = splitedName.minBy {  it.length }
        return simpleName?:""
    }

    fun getGreetings() : String {
        val cal = Calendar.getInstance()
        cal.time = Date()
        return when (cal.get(Calendar.HOUR_OF_DAY)) {
            in 3..8 -> {
                "Selamat Pagi"
            }
            in 9..14 -> {
                "Selamat Siang"
            }
            in 15..18 -> {
                "Selamat Sore"
            }
            else -> {
                "Selamat Malam"
            }
        }
    }
}