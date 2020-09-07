package com.example.parsejson.BroadcastReceiverPackage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.os.Build;

import android.app.NotificationManager;
import android.app.PendingIntent;

import androidx.core.app.NotificationCompat;

import com.example.parsejson.PagesPackage.MainActivity;
import com.example.parsejson.R;

// BroadcastReceiver are check if the phone are charged
public class BroadcastReceiverBattery3 extends BroadcastReceiver {

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private static PendingIntent pendingIntent;
    private final int NOTIFY_ID = 3; // ID of notification
    private String id = "3"; // default_channel_id
    private String title = "BFF Battery"; // Default Channel

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        assert action != null;
        switch (action) {
            case Intent.ACTION_POWER_CONNECTED:
                if (notificationManager == null) {
                    notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel mChannel = notificationManager.getNotificationChannel(id);
                    if (mChannel == null) {
                        mChannel = new NotificationChannel(id, title, importance);
                        mChannel.enableVibration(true);
                        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                        notificationManager.createNotificationChannel(mChannel);
                    }
                    getBuilder(context, id, "The phone are charged", title);
                } else {
                    getBuilder(context, id, "The phone are charged", title);
                }
                Notification notification = builder.build();
                notificationManager.notify(NOTIFY_ID, notification);
                break;
        }
    }

    private void getBuilder(Context context, String id, String contentText, String title) {
        builder = new NotificationCompat.Builder(context, id);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(context, 1, intent, 0);
        builder.setContentTitle(title)
                .setContentText(contentText)  // required
                .setSmallIcon(R.drawable.ic_launcher_foreground)  // required
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setTicker(title)
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                .setPriority(Notification.PRIORITY_HIGH);
    }

}
