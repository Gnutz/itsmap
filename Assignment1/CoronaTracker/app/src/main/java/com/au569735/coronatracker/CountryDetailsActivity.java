package com.au569735.coronatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDetailsActivity extends AppCompatActivity {

    ImageView imgFlagIcon;
    TextView txtCountry, txtCases, txtDeaths, txtRating, txtNotes;
    Button btnBack, btnEdit;

    CountryCoronaStatistic countryCoronaStatistic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        imgFlagIcon = findViewById(R.id.imgDetailFlagIcon);
        txtCountry = findViewById(R.id.txtCDetailCountryName);
        txtCases = findViewById(R.id.txtDetailCases);
        txtDeaths = findViewById(R.id.txtDetailDeaths);
        txtRating = findViewById(R.id.txtDetailUserRating);
        txtNotes = findViewById(R.id.txtDetailUserNotes);

        Intent passedIntent = getIntent();
        countryCoronaStatistic = (CountryCoronaStatistic) passedIntent.getSerializableExtra(Constants.STAT_BLOCK);
        updateUI();

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplication(), EditContryStatActivity.class);
               intent.putExtra(Constants.STAT_BLOCK, countryCoronaStatistic);
               startActivity(intent);
               finish();
            }
        });
    }

    private void updateUI() {
        imgFlagIcon.setImageResource(countryCoronaStatistic.FlagIconId);
        txtCountry.setText(countryCoronaStatistic.Country);
        txtCases.setText(""+countryCoronaStatistic.NumberOfCases);
        txtDeaths.setText(""+countryCoronaStatistic.NumberOfDeaths);
        txtRating.setText(""+ countryCoronaStatistic.UserRating);
        txtNotes.setText(countryCoronaStatistic.UserNote);
    }
}