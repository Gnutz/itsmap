package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String helloAndroidMessage = "Hello Android";
    private TextView txtMain;
    private Button btnChange;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //references views
        txtMain = findViewById(R.id.textView);
        btnChange = findViewById(R.id.button);
        btnExit = findViewById(R.id.btnExit);


        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               txtMain.setText(helloAndroidMessage);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}