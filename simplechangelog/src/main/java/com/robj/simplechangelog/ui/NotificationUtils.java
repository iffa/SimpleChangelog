package com.robj.simplechangelog.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NotificationCompat;

import com.robj.simplechangelog.R;

/**
 * @author Rob J
 * @author Santeri Elo
 */
class NotificationUtils {
    private static final int CHANGELOG_ID = 2;

    static void showChangelogNotification(Context context, Intent i, String channelId, @DrawableRes int iconResId,
                                          String title, String body) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setAutoCancel(true)
                        .setOngoing(false)
                        .setSmallIcon(iconResId)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setTicker(title)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                        .setCategory(NotificationCompat.CATEGORY_STATUS)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Add notification channel for Oreo and up
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = context.getString(R.string.cl_notification_channel);
            int importance = NotificationManager.IMPORTANCE_LOW; // No sound, but appears in status bar

            NotificationChannel channel = new NotificationChannel(channelId,
                    channelName,
                    importance);

            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(CHANGELOG_ID, mBuilder.build());
    }

    static void cancelNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(CHANGELOG_ID);
    }

}
