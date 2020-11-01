package com.example.parsejson.BroadcastReceiversPackage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.parsejson.PagesPackage.MainActivity;
import com.example.parsejson.R;

// BroadcastReceiver are check if the phone are charged
public class BroadcastReceiverBattery extends BroadcastReceiver {

    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private PendingIntent pendingIntent;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        assert action != null;
        switch (action) {
            case Intent.ACTION_POWER_CONNECTED:
                final int NOTIFY_ID = 1; // ID of notification
                String id = "1"; // default_channel_id
                String title = "MyBroadcastReceiver"; // Default Channel
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
                    getBuilder(context, id);
                } else {
                    getBuilder(context, id);
                }
                Notification notification = builder.build();
                notificationManager.notify(NOTIFY_ID, notification);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getBuilder(Context context, String id) {
        builder = new NotificationCompat.Builder(context, id);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(context, 1, intent, 0);
        builder.setContentTitle("MyBroadcastReceiver")
                .setContentText("MyBroadcastReceiver")  // required
                .setSmallIcon(R.mipmap.ic_launcher)  // required
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setTicker("MyBroadcastReceiver")
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                .setPriority(NotificationManager.IMPORTANCE_HIGH);
    }

}
