package com.kikidinda.hitrash.model

data class Notification(
    val to: String = "",
    val data: Data
) {
    data class Data(
        val message: String = ""
    )
}

data class NotificationResponse(
    val success: Int = 0,
    val failure: Int = 0
)