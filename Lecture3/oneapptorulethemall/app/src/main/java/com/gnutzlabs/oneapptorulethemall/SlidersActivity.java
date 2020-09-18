package com.gnutzlabs.oneapptorulethemall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class SlidersActivity extends AppCompatActivity {


    //widgits
    SeekBar skbRed, skbGreen, skbBlue;
    Button btnOk, btnCancel;
    ConstraintLayout layoutSliders;

    //state
    int redColorComponent, greenColorComponent, blueColorComponent ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliders);

        layoutSliders = findViewById(R.id.clSliders);


        //map widigts
        skbRed = findViewById(R.id.skbRed);
        redColorComponent = skbRed.getProgress();
        skbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redColorComponent = progress;
                updateBackgroundColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skbGreen = findViewById(R.id.skbGreen);
        greenColorComponent = skbGreen.getProgress();
        skbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenColorComponent = progress;
                updateBackgroundColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skbBlue = findViewById(R.id.skbBlue);
        blueColorComponent = skbBlue.getProgress();
        skbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueColorComponent = progress;
                updateBackgroundColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        updateBackgroundColor();

        btnCancel = findViewById(R.id.btnCancel_Sliders);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnOk = findViewById(R.id.btnOk_sliders);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, new Intent().putExtra(Constants.SLIDERS_RESULT,
                        String.format("rgb(%d,%d,%d)", redColorComponent, greenColorComponent, blueColorComponent)));
                finish();
            }
        });




    }

    private void updateBackgroundColor() {
        layoutSliders.setBackgroundColor(Color.rgb(redColorComponent, greenColorComponent, blueColorComponent));
    }


}