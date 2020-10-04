package com.au569735.coronatracker.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.au569735.coronatracker.adaptors.CountryStatisticAdapter;
import com.au569735.coronatracker.R;
import com.au569735.coronatracker.model.CountryStatistic;
import com.au569735.coronatracker.utils.Constants;
import com.au569735.coronatracker.viewmodels.CountryStatisticListViewModel;

import java.util.ArrayList;

public class CountryListActivity extends AppCompatActivity
implements CountryStatisticAdapter.ICountryStatisticItemClickedListener {

    RecyclerView rcvCountryStats;
    Button btnExit;
    CountryStatisticAdapter adapter;
    CountryStatisticListViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        adapter = new CountryStatisticAdapter(this);
        rcvCountryStats = findViewById(R.id.rcvStatistics);
        rcvCountryStats.setLayoutManager(new LinearLayoutManager(this));
        rcvCountryStats.setAdapter(adapter);

        vm =  new ViewModelProvider(this).get(CountryStatisticListViewModel.class);
        vm.getStatisticsLiveData().observe(this, new Observer<ArrayList<CountryStatistic>>() {
            @Override
            public void onChanged(ArrayList<CountryStatistic> countryStatistics) {
                adapter.updateStatistics(countryStatistics);
            }
        });

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void onStatItemClicked(int index) {
        launchDetailsActivity(index);
    }

    void launchDetailsActivity(int index){
        Intent intent = new Intent(this, CountryDetailsActivity.class);
        intent.putExtra(Constants.STAT_BLOCK, vm.getStatistics().get(index));
        startActivityForResult(intent, Constants.REQUEST_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            vm.updateStatistic((CountryStatistic) data.getSerializableExtra(Constants.STAT_BLOCK));
        }

    }
}