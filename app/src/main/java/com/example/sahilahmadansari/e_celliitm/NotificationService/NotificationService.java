package com.example.sahilahmadansari.e_celliitm.NotificationService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.example.sahilahmadansari.e_celliitm.MainActivity;
import com.example.sahilahmadansari.e_celliitm.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class NotificationService extends FirebaseMessagingService {
    NotificationManager notificationManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            setUpNotificationChannels();
        }

        int notificationId=new Random().nextInt(6000);

        Intent notificationIntent;
        if(MainActivity.isAppRunning){
            notificationIntent=new Intent(this, MainActivity.class);
        }else{
            notificationIntent=new Intent(this, MainActivity.class);
        }

        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.esummit_logo_black)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getNotification().getBody()))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setUpNotificationChannels(){
        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel("default", "default", NotificationManager.IMPORTANCE_LOW);
        adminChannel.setDescription("default");
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }
    }
}
