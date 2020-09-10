package smap.kga.thereandbackagain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACT_REQUEST = 0;
    TextView txtHeader;
    Button btnLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHeader  = findViewById(R.id.txtActivityHeader);
        btnLaunch = findViewById(R.id.btn_launch);

        btnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSecondActivity();
            }
        });
    }

    void launchSecondActivity(){
        startActivityForResult(
                new Intent(this, SecondActivity.class),
                SECOND_ACT_REQUEST);
    }
}