package com.taknikiniga.internetspeedtest.services

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.taknikiniga.internetspeedtest.R
import com.taknikiniga.internetspeedtest.fragments.home.viewmodel.HomeViewModel
import com.taknikiniga.internetspeedtest.main.MainActivity
import kotlin.contracts.Returns

class BoundService : Service() {

    companion object{
        val CHANNEL_NAME = "speed_channel"
        val CHANNEL_ID = 1
        var DOWNLOAD_SPEED =""
        var UPLOAD_SPEED =""
    }


    override fun onBind(intent: Intent?): IBinder? = null


    override fun onCreate() {
        super.onCreate()
        speedNotification(download = DOWNLOAD_SPEED, upload = UPLOAD_SPEED)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

    }

    fun speedNotification(download:String="",upload:String="") {
        var notification: Notification
        val pendingIntent = PendingIntent.getActivity(this,0, Intent(
            this,MainActivity::class.java
        ),PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = NotificationCompat.Builder(this, CHANNEL_NAME)
                .setSmallIcon(R.drawable.ic_baseline_shutter_speed_24)
                .setContentIntent(pendingIntent)
                .setContentTitle("Download: $download Mbps | Upload: $upload Mbps" )
                .setAutoCancel(false)
                .build()

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel = NotificationChannel(
                CHANNEL_NAME,
                "Internet Speed",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
            notificationManager.notify(CHANNEL_ID, notification)
        } else {
            notification = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_baseline_shutter_speed_24).setContentTitle("InternetSpeed").build()
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(CHANNEL_ID,notification)
        }
    }

//    @SuppressLint("RemoteViewLayout")
//    fun notificationRemoteView(intent: Intent?): RemoteViews {
//
//        return RemoteViews(packageName,R.layout.internet_notification_lyt).also {
//            it.setTextViewText(R.id.idDownSpeed,"${intent?.getStringExtra("download_speed")}")
//            it.setTextViewText(R.id.icUploadSpeed,"${intent?.getStringExtra("upload_speed")}")
//        }
//    }

}