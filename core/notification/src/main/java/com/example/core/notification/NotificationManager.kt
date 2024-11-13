package com.example.core.notification

import PushMessage
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.compose.core.notification.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    companion object {
        private const val CHANNEL_ID = "compose_notification_chanel"
        private const val CHANNEL_NAME = "compose_notification_chanel_name"
        const val KEY_PUSH_MESSAGE = "push_message"
    }

    fun showPushNotification(
        pushMessage: PushMessage,
        activityClass: Class<*>,
    ) {
        if (shouldShowPushNotification(pushMessage)) {
            val notificationId = getNotificationId(pushMessage)
            val notificationManager = getNotificationManager()

            val builder = buildNotification(
                notificationId = notificationId,
                pushMessage = pushMessage,
                activityClass = activityClass
            )
            notificationManager?.notify(notificationId, builder.build())
        }
    }

    private fun getNotificationId(pushMessage: PushMessage): Int {
        return if (pushMessage.intercomConversationId.isNotEmpty()) {
            pushMessage.intercomConversationId.hashCode()
        } else {
            pushMessage.toString().hashCode()
        }
    }

    private fun getNotificationManager(): NotificationManager? {
        val mChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        notificationManager?.createNotificationChannel(mChannel)

        return notificationManager
    }

    private fun buildNotification(
        notificationId: Int,
        pushMessage: PushMessage,
        activityClass: Class<*>,
    ): NotificationCompat.Builder {
        val contentIntent = getPendingIntent(
            notificationId = notificationId,
            pushMessage = pushMessage,
            activityClass = activityClass
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_push_notification)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(contentIntent)
            .setContentText(pushMessage.body)


        if (pushMessage.title.isNotEmpty()) {
            builder.setContentTitle(pushMessage.title)
        }

        return builder
    }

    private fun getPendingIntent(
        notificationId: Int,
        pushMessage: PushMessage,
        activityClass: Class<*>,
    ): PendingIntent {
        val notificationIntent = Intent(context, activityClass).apply {
            action = Intent.ACTION_MAIN
            addCategory(Intent.CATEGORY_LAUNCHER)
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(KEY_PUSH_MESSAGE, pushMessage)
        }

        return PendingIntent.getActivity(
            context,
            notificationId,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun shouldShowPushNotification(pushMessage: PushMessage): Boolean {
      /*  return if (pushMessage.notificationType == NotificationType.UNKNOWN ) {
            false
        } else {
            true
        }*/

        return true
    }

    fun clearAllNotifications() {
        NotificationManagerCompat.from(context).cancelAll()
    }
}