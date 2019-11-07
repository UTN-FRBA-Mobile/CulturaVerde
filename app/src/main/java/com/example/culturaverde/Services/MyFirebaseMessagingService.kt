package com.example.culturaverde.Services

import android.app.Notification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.culturaverde.Activities.MainActivity
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.culturaverde.R

const val NOTIFICATION_CHANNEL_ID = "101"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val data = remoteMessage.data
        Log.d("MyFirebaseMessagingServ", data.toString())
        Config.title = data["title"]
        Config.content = data["content"]
        Config.imageUrl = data["imageUrl"]
        Config.gameUrl = data["gameUrl"]
        sendNotification()
    }

    private fun sendNotification() {
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notification",
                NotificationManager.IMPORTANCE_HIGH
            )

            //Configure Notification Channel
            notificationChannel.description = "Game Notifications"
            notificationChannel.enableLights(true)
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)

            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(Config.title)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.bell)
            .setSound(defaultSound)
            .setContentText(Config.content)
            .setContentIntent(pendingIntent)
            .setWhen(System.currentTimeMillis())
            .setPriority(Notification.PRIORITY_MAX)


        notificationManager.notify(1, notificationBuilder.build())


    }

}