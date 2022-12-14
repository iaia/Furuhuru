package dev.iaiabot.furuhuru.android.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import dev.iaiabot.furuhuru.android.R
import dev.iaiabot.furuhuru.android.issue.IssueActivity

internal object FuruhuruNotification {
    enum class Channels(val channelId: String, val channelName: String) {
        Furuhuru("_Furuhuru_CHANNEL", "Furuhuru"),
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(notificationManager: NotificationManager) {
        Channels.values().forEach { channel ->
            if (notificationManager.getNotificationChannel(channel.channelId) == null) {
                val notificationChannel = NotificationChannel(
                    channel.channelId,
                    channel.channelName,
                    NotificationManager.IMPORTANCE_LOW
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    notificationChannel.setAllowBubbles(true)
                }
                notificationManager.createNotificationChannel(
                    notificationChannel
                )
            }
        }
    }

    fun createSensorNotification(context: Context): Notification {
        val contentIntent = createContentIntent(context)
        return NotificationCompat
            .Builder(context, Channels.Furuhuru.channelId)
            .setSmallIcon(R.drawable.ic_send) // TODO: Furuhuruのアイコン作る
            .setContentTitle("Furuhuru is running")
            .setContentIntent(contentIntent) // 二重に設定する?
            .setCategory(Notification.CATEGORY_CALL)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    bubbleMetadata = createBubbleMetaData(context, contentIntent)
                }
            }
            .build()
    }


    private fun createContentIntent(context: Context): PendingIntent {
        val target = IssueActivity.createIntent(context)
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                createContentIntentForS(context, target)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                createContentIntentForM(context, target)
            }
            else -> {
                createContentIntentForLessThanM(context, target)
            }
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun createContentIntentForLessThanM(context: Context, target: Intent): PendingIntent {
        return PendingIntent.getActivity(
            context,
            0,
            target,
            PendingIntent.FLAG_UPDATE_CURRENT,
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun createContentIntentForM(context: Context, target: Intent): PendingIntent {
        return PendingIntent.getActivity(
            context,
            0,
            target,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun createContentIntentForS(context: Context, target: Intent): PendingIntent {
        return PendingIntent.getActivity(
            context,
            0,
            target,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun createBubbleMetaData(
        context: Context,
        contentIntent: PendingIntent
    ): NotificationCompat.BubbleMetadata {
        return NotificationCompat.BubbleMetadata
            .Builder()
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_send))
            .setIntent(contentIntent)
            .setDesiredHeight(600)
            .setAutoExpandBubble(false)
            .setSuppressNotification(false)
            .build()
    }
}
