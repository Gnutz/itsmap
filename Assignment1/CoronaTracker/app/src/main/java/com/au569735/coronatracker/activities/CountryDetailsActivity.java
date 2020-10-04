package com.au569735.coronatracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.au569735.coronatracker.viewmodels.CountryStatisticViewModel;
import com.au569735.coronatracker.R;
import com.au569735.coronatracker.model.CountryStatistic;
import com.au569735.coronatracker.utils.Constants;

public class CountryDetailsActivity extends AppCompatActivity {

    ImageView imgFlagIcon;
    TextView txtCountry, txtCases, txtDeaths, txtRating, txtNotes;
    Button btnBack, btnEdit;

    CountryStatisticViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        vm = new ViewModelProvider(this).get(CountryStatisticViewModel.class);
        vm.getCountryStatistic().observe(this, new Observer<CountryStatistic>() {
            @Override
            public void onChanged(CountryStatistic newStats) {
                    updateUI(newStats);
            }
        });

        imgFlagIcon = findViewById(R.id.imgDetailFlagIcon);
        txtCountry = findViewById(R.id.txtCDetailCountryName);
        txtCases = findViewById(R.id.txtDetailCases);
        txtDeaths = findViewById(R.id.txtDetailDeaths);
        txtRating = findViewById(R.id.txtDetailUserRating);
        txtNotes = findViewById(R.id.txtDetailUserNotes);

        Intent passedIntent = getIntent();
       CountryStatistic countryStatistic = (CountryStatistic) passedIntent.getSerializableExtra(Constants.STAT_BLOCK);
       vm.updateCountryStatistic(countryStatistic);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(getApplication(), CountryEditActivity.class);
               intent.putExtra(Constants.STAT_BLOCK, vm.getCountryStatistic().getValue());
               intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
               startActivity(intent);
               finish();
            }
        });
    }

    private void updateUI(CountryStatistic stats) {
        imgFlagIcon.setImageResource(stats.FlagIconId);
        txtCountry.setText(stats.Country);
        txtCases.setText(""+ stats.Cases);
        txtDeaths.setText(""+ stats.Deaths);
        txtRating.setText(String.format("%.1f", stats.Rating));
        txtNotes.setText(stats.Note);
    }
}