package com.kikidinda.hitrash.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.local.LocalStorage
import com.kikidinda.hitrash.ui.splashscreen.SplashscreenActivity

class NotificationService : FirebaseMessagingService() {
    companion object {
        const val CHANNEL_ID = "admin_chanel_1"
        private const val CHANNEL_NAME = "Hi Trash"
        private const val NOTIFICATION_REQUEST_CODE = 200

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val msg = message.data["message"]

        sendNotif(msg ?: "")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        val id = LocalStorage.getUser(applicationContext).id
        FirestoreUser.sendToken(id!!, token)
    }

    private fun sendNotif(message: String) {
        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_foreground)
        val intent = Intent(this, SplashscreenActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Informasi")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setLargeIcon(largeIcon)
            .setAutoCancel(true)
        //.setLargeIcon(largeIcon)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            mBuilder.setChannelId(CHANNEL_ID)
            mNotificationManager.createNotificationChannel(channel)
        }
        val notification = mBuilder.build()
        mNotificationManager.notify(Math.random().toInt(), notification)
    }

}