package com.gnutzlabs.oneapptorulethemall;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtHeading;
    Button btnPicker;
    Button btnEditText;
    Button btnSliders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHeading = findViewById(R.id.txtHeading);
        btnPicker = findViewById(R.id.btnPicker);
        btnEditText = findViewById(R.id.btnEditText);
        btnSliders = findViewById(R.id.btnSliders);

        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                launchPickerActivity();

            }
        });

        btnEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), EditTextActivity.class), Constants.EDIT_TEXT_REQUEST);
            }
        });

        btnSliders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), SlidersActivity.class), Constants.SLIDERS_REQUEST);
            }
        });

    }

    void launchPickerActivity(){
        startActivityForResult(new Intent(this, PickerActivity.class), Constants.PICKER_REQUST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case Constants.PICKER_REQUST:
                    int result = data.getIntExtra(Constants.PICKER_RESULT, 0);
                    makeToast("Result from PickerActivity is: " + result);
                    break;
                case Constants.EDIT_TEXT_REQUEST:
                    ArrayList<String>  editValues = data.getStringArrayListExtra(Constants.EDIT_RESULT);
                    String editToast = "Result from EditActivity is: ";
                    for (String editValue : editValues) {
                        editToast += editValue + ", ";
                    }
                    makeToast(editToast);
                    break;
                case Constants.SLIDERS_REQUEST:
                    String rgbResult = data.getStringExtra(Constants.SLIDERS_RESULT);
                    makeToast("Result from SlidersActivity is:" + rgbResult);
                    break;

        }
        else makeToast("The chosen Activity was cancelled");

    }

    void makeToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

