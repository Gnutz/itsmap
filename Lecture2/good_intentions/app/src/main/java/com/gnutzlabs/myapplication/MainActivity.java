package com.gnutzlabs.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Intent.ACTION_DIAL;
import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.ACTION_SENDTO;
import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;
import static android.provider.MediaStore.ACTION_VIDEO_CAPTURE;

public class MainActivity extends AppCompatActivity {

    TextView txtHeader;
    Button btnPictureAction;
    Button btnVideoAction;
    Button btnEmailAction;
    Button btnSmsAction;
    Button btnPhoneCallAction;
    Button btnAlarmAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //region set up widgits

        txtHeader = findViewById(R.id.txtHeader);
        btnPictureAction = findViewById(R.id.btnPictureAction);
        btnVideoAction = findViewById(R.id.btnVideoAction);
        btnEmailAction = findViewById(R.id.btnEmailAction);
        btnSmsAction = findViewById(R.id.btnSmsAction);
        btnPhoneCallAction = findViewById(R.id.btnPhoneCallAction);
        btnAlarmAction = findViewById(R.id.btnAlarmAction);

        //endregion

        //region OnClickListeners

        //intents set by studying https://developer.android.com/guide/components/intents-common#Messaging

        btnPictureAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(ACTION_IMAGE_CAPTURE);
               startActivity(intent);
            }
        });

        btnVideoAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_VIDEO_CAPTURE);
                startActivity(intent);
            }
        });

        btnEmailAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivityByAction(ACTION_SEND);
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                startActivity(intent);

            }
        });

        btnSmsAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"));
                startActivity(intent);
            }
        });

        btnPhoneCallAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_DIAL);
                startActivity(intent);
            }
        });

        btnAlarmAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // example from https://developer.android.com/guide/components/intents-common#CreateTimer
                Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, 15);
                alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, 20);
                startActivity(alarmIntent);
            }}
        );

        //endregion

    }

}