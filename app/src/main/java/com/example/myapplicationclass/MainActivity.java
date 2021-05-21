package com.example.myapplicationclass;

//import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;

//import androidx.core.app.NotificationCompat;
//import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends WearableActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();

    }
    // class -1 2
    public void notifyMe(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANEL_ID = "my_channel_id_o1";
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
             NotificationChannel notificationChannel =
                     new NotificationChannel(NOTIFICATION_CHANEL_ID,"My notification",
                             NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
         }

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, NOTIFICATION_CHANEL_ID);

         notificationBuilder.setAutoCancel(true)
                 //icon
                 .setSmallIcon(R.drawable.ic_full_sad)
                 // Title
                .setContentTitle("My notification")
                 // contend
                .setContentText("hello from here!")
                 //info
                 .setContentInfo("info");

         notificationManager.notify(1, notificationBuilder.build());
    }
}