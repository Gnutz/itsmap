package com.gnutzlabs.oneapptorulethemall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class PickerActivity extends AppCompatActivity {

    TextView txtPickerValueDisplay;
    SeekBar skbPicker;
    Button btnOk;
    Button btnCancel;

    PickerViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        txtPickerValueDisplay = findViewById(R.id.txtPickervalueDisplay);
        skbPicker = findViewById(R.id.skbarPicker);
        btnCancel = findViewById(R.id.btnCancel);
        btnOk = findViewById(R.id.btnOk);

        vm =  new ViewModelProvider(this).get(PickerViewModel.class);
        vm.getValue().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                updateUI(integer);
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, new Intent().putExtra(Constants.PICKER_RESULT, vm.getValue().getValue()));
                finish();
            }

        });

        skbPicker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                vm.setPickerValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        vm.setPickerValue(skbPicker.getProgress());

    }

    private void updateUI(Integer integer) {
        txtPickerValueDisplay.setText("Selected: " + integer);
    }
}