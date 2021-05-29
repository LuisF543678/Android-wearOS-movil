package com.example.mobilepp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class NotifycationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifycation_details);

        CharSequence charSequence = getMessageText(getIntent());
        String reply_was = getResources().getString(R.string.reply_was);
        Toast.makeText(this, reply_was+charSequence, Toast.LENGTH_LONG).show();
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput !=null) {
            return remoteInput.getCharSequence((NotificationUtils.EXTRA_VOICE_REPLAY));
        }
        return null;

    }
}