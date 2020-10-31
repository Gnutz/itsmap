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
import android.widget.Toast;

import com.au569735.coronatracker.viewmodels.CountryStatisticDetailViewModel;
import com.au569735.coronatracker.viewmodels.CountryStatisticEditViewModel;
import com.au569735.coronatracker.R;
import com.au569735.coronatracker.model.CountryStatistic;
import com.au569735.coronatracker.utils.Constants;
import com.bumptech.glide.Glide;

public class CountryDetailsActivity extends AppCompatActivity {

    ImageView imgFlagIcon;
    TextView txtCountry, txtCases, txtDeaths, txtRating, txtNotes;
    Button btnBack, btnEdit, btnDelete;

    CountryStatisticDetailViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        vm = new ViewModelProvider(this).get(CountryStatisticDetailViewModel.class);
        vm.getCountryStatisticLiveData().observe(this, new Observer<CountryStatistic>() {
            @Override
            public void onChanged(CountryStatistic newStats) {
                    updateUI(newStats);
            }
        });

        //widgets
        imgFlagIcon = findViewById(R.id.imgDetailFlagIcon);
        txtCountry = findViewById(R.id.txtCDetailCountryName);
        txtCases = findViewById(R.id.txtDetailCases);
        txtDeaths = findViewById(R.id.txtDetailDeaths);
        txtRating = findViewById(R.id.txtDetailUserRating);
        txtNotes = findViewById(R.id.txtDetailUserNotes);

        if(vm.getCountryStatistic() == null ) {
            Intent passedIntent = getIntent();
            int statisticId = passedIntent.getIntExtra(Constants.COUNTRY_ID, -1);
            vm.selectStatisticById(statisticId);
        }

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
               intent.putExtra(Constants.COUNTRY_ID, vm.getCountryStatistic().getUid());
               startActivity(intent);
               finish();
            }
        });

        btnDelete  = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryStatistic currentStatistic = vm.getCountryStatistic();
                vm.deleteStatistic(vm.getCountryStatistic());
                finish();
                Toast.makeText(getApplicationContext(), ""+currentStatistic.getCountry() +" has been removed", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void updateUI(CountryStatistic stats) {
        Glide.with(imgFlagIcon.getContext())
                .load(stats.getImage())
                .placeholder(R.drawable.placeholder)
                .into(imgFlagIcon);
        txtCountry.setText(stats.getCountry());
        txtCases.setText(""+ stats.getCases());
        txtDeaths.setText(""+ stats.getDeaths());
        txtRating.setText(String.format("%.1f", stats.getRating()));
        txtNotes.setText(stats.getNote());
    }
}