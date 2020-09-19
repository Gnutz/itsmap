package com.au569735.coronatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class EditContryStatActivity extends AppCompatActivity {

    ImageView imgFlagIcon;
    TextView txtCountry, txtRating;
    SeekBar skbRating;
    EditText etxtNote;
    Button btnCancel, btnOk;

    CountryCoronaStatistic countryCoronaStatistic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_country_stat);

        imgFlagIcon = findViewById(R.id.imgEditFlagIcon);
        txtCountry = findViewById(R.id.txtEditCountryName);
        txtRating = findViewById(R.id.txtEditUserRating);
        skbRating = findViewById(R.id.skbUserRating);

        Intent passedIntent = getIntent();
        countryCoronaStatistic = (CountryCoronaStatistic) passedIntent.getSerializableExtra(Constants.STAT_BLOCK);

        skbRating = findViewById(R.id.skbUserRating);
        skbRating.setProgress((int) countryCoronaStatistic.UserRating*10);
        skbRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countryCoronaStatistic.UserRating = (float) progress/10;
                updateUI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        etxtNote = findViewById(R.id.editTxtUserNotes);


        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateUI();

        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void updateUI() {
        imgFlagIcon.setImageResource(countryCoronaStatistic.FlagIconId);
        txtCountry.setText(countryCoronaStatistic.Country);
        txtRating.setText(String.format("%.1f", countryCoronaStatistic.UserRating));
        etxtNote.setText(countryCoronaStatistic.UserNote);
    }


}