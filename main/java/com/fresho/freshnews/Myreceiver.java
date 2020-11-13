package com.fresho.freshnews;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Myreceiver extends BroadcastReceiver {

    public static final String channel3="channel3";
    NotificationManagerCompat notificationManagerCompat;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManagerCompat=NotificationManagerCompat.from(context);

        if(Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())){


            NotificationChannel notificationChannel = new NotificationChannel(channel3,"channel3", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Welcome");
            notificationChannel.enableVibration(true);


            NotificationManager manager = context.getSystemService(NotificationManager.class);

            manager.createNotificationChannel(notificationChannel);
            notificationManagerCompat=NotificationManagerCompat.from(context);

            Notification notification = new NotificationCompat.Builder(context,channel3)
                    .setContentTitle("Gear up")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
                    .setContentText("Get latest news")
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();
            notificationManagerCompat.notify(3,notification);


        }

    }
}
