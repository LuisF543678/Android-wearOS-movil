package com.example.mobilepp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.RemoteInput;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class NotifycationDetails extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifycation_details);


        CharSequence charSequence = getMessageText(getIntent());
         /*
        String reply_was = getResources().getString(R.string.reply_was);
        Toast.makeText(this, reply_was+charSequence, Toast.LENGTH_LONG).show();
         */
        String result = charSequence.toString();

        if (result.equalsIgnoreCase("yes")){
            // Open map


        }else if (result.equalsIgnoreCase("no")){
            // send sms
            //-- linea agregda
            //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.SEND_SMS)){

                }else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
            }
            //--
        }else if (result.equalsIgnoreCase("may be")){
            // show toast
        }
    }


    public void onRequestPermissionsResults(int requestCode, String permissions[], int[] grantResult){
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResult.length>0 && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Thanks for permitting!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "Why did you allow me to send?", Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput !=null) {
            return remoteInput.getCharSequence((NotificationUtils.EXTRA_VOICE_REPLAY));
        }
        return null;

    }
}