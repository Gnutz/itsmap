package com.gnutzlabs.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Intent.ACTION_SEND;
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

        initializeWidgets();

        btnPictureAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByAction(ACTION_IMAGE_CAPTURE);
            }
        });

        btnVideoAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByAction(ACTION_VIDEO_CAPTURE);
            }
        });

        btnEmailAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByAction(ACTION_SEND);
            }
        });

        btnSmsAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByAction();
            }
        });
    }

    void startActivityByAction(String action){
        Intent intent = new Intent();
        intent.setAction(action);
        startActivity(intent);
    }

    void initializeWidgets() {
        txtHeader = findViewById(R.id.txtHeader);
        btnPictureAction = findViewById(R.id.btnPictureAction);
        btnVideoAction = findViewById(R.id.btnVideoAction);
        btnEmailAction = findViewById(R.id.btnEmailAction);
        btnSmsAction = findViewById(R.id.btnSmsAction);
        btnPhoneCallAction = findViewById(R.id.btnPhoneCallAction);
        btnAlarmAction = findViewById(R.id.btnAlarmAction);
    }
}