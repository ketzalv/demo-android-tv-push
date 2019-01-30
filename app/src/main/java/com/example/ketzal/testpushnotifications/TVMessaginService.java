package com.example.ketzal.testpushnotifications;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class TVMessaginService extends FirebaseMessagingService {
    public static final String KEY_MESSAGE = "broadcast_message";
    public static final String TAG = "PushMessagingService";
    public static final String SHOW_MESSAGE = "SHOW_MESSAGE";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ false) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow("Se recibio push");
            }

        }
        handleNow(remoteMessage.getNotification().getBody());

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "Refreshed token: " + s);

    }

    private void handleNow(String message) {
        Intent intent = new Intent();
        intent.putExtra(KEY_MESSAGE, message);
        intent.setAction(SHOW_MESSAGE);
        sendBroadcast(intent);
    }

    private void scheduleJob() {

    }
}