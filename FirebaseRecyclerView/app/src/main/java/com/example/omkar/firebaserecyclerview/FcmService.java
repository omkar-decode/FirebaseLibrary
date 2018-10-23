package com.example.omkar.firebaserecyclerview;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "library_channel_1";
    private NotificationManager manager;

//    public FcmService (Context context) {
//        createNotificationChannel(context);
//    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("Sender", "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.e("Received", remoteMessage.getData().toString());

            String notifTitle = remoteMessage.getData().get("title");
            String bookName = remoteMessage.getData().get("name");
            String bookAuthor = remoteMessage.getData().get("author");

//            showNotification(getApplicationContext(), bookName, bookAuthor);
            Notification.Builder builder = FcmService.showNotification(
                    getApplicationContext(), notifTitle, bookName, bookAuthor);
            FcmService.getManager(getApplicationContext()).notify(101, builder.build());
        }
    }

    public static void createNotificationChannel (Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "Channel1";
            String channelDescription = "Library channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel libraryChannel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            libraryChannel.setDescription(channelDescription);

            libraryChannel.enableVibration(true);
            libraryChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            getManager(context).createNotificationChannel(libraryChannel);
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
        }
    }

    public static NotificationManager getManager (Context context) {

        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    }

    @TargetApi(Build.VERSION_CODES.O)
    public static Notification.Builder showNotification (Context context, String title,
                                                         String bookName, String bookAuthor) {

        return new Notification.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bookName + " , by " + bookAuthor)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp);
    }
}
