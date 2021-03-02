package com.esunsoft2020.donggo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFCMService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i("MyFCMService","onMessageReceived....");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder= null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel("ch1", "push ch", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            builder= new NotificationCompat.Builder(this, "ch1");
        }else{
            builder= new NotificationCompat.Builder(this, null);
        }

        String fromWho= remoteMessage.getFrom();
        String notiTitle= "title";
        String notiText= "message";
        if( remoteMessage.getNotification() !=null ){
            notiTitle= remoteMessage.getNotification().getTitle();
            notiText= remoteMessage.getNotification().getBody();
        }

        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle(notiTitle);
        builder.setContentText(notiText);
        builder.setAutoCancel(true);

        Map<String, String> data = remoteMessage.getData();
        if(data!=null){
            String name= data.get("name");
            String message= data.get("msg");

            Intent intent= new Intent(this, IntroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //바로 실행되지 않고 알림에 보관되어 있다가 실행되야 하므로 보류중인 인텐트로 변경
            PendingIntent pendingIntent= PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
        }
        Notification notification= builder.build();
        startForeground(200, notification);

    }
}
