package com.example.clickcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    TextView txtCounter;
    Button btnCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //references for views
        txtCounter = findViewById(R.id.txtCounter);
        btnCounter = findViewById(R.id.button);

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                updateUI();
            }
        });

        //first update resets counter text
        updateUI();


    }

    void updateUI(){
        txtCounter.setText("Count: " + count);
    }
}