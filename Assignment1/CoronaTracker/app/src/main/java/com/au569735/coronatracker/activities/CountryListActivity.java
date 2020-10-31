package com.au569735.coronatracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.au569735.coronatracker.adapters.CountryStatisticAdapter;
import com.au569735.coronatracker.R;
import com.au569735.coronatracker.model.CountryStatistic;
import com.au569735.coronatracker.utils.Constants;
import com.au569735.coronatracker.viewmodels.CountryStatisticListViewModel;

import java.util.List;

public class CountryListActivity extends AppCompatActivity
implements CountryStatisticAdapter.ICountryStatisticItemClickedListener {

    RecyclerView rcvCountryStats;
    Button btnExit, btnAdd;
    EditText etxtSearch;
    CountryStatisticAdapter adapter;
    CountryStatisticListViewModel vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //setting up viewModel and adapter
        vm =  new ViewModelProvider(this).get(CountryStatisticListViewModel.class);
        adapter = new CountryStatisticAdapter(this);

        //recyclerview setup
        rcvCountryStats = findViewById(R.id.rcvStatistics);
        rcvCountryStats.setLayoutManager(new LinearLayoutManager(this));
        rcvCountryStats.setAdapter(adapter);

        //observing changes to the
        vm.getStatisticsLiveData().observe(this, new Observer<List<CountryStatistic>>() {
            @Override
            public void onChanged(List<CountryStatistic> countryStatistics) {
                adapter.updateStatistics(countryStatistics);
            }
        });

        //exit button setup
        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //add button setup
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "add clicked", Toast.LENGTH_SHORT)
                        .show();
            }
        });


        //etxtSearch setup
        // hide keyboard when editText loses focus
        // #1: set OnFocusChangedListener for the input field
        // #2 Override the activity's DispatchTouchEvent method see below
        // snippets copied from https://stackoverflow.com/questions/4165414/how-to-hide-soft-keyboard-on-android-after-clicking-outside-edittext?page=2&tab=votes#tab-top
        etxtSearch = findViewById(R.id.etxtSearch);
        etxtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Toast.makeText(getApplicationContext(), "search lost focus", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });


    }

    @Override
    public void onStatItemClicked(int index) {
        launchDetailsActivity(index);
    }

    void launchDetailsActivity(int index){
        Intent intent = new Intent(this, CountryDetailsActivity.class);
        intent.putExtra(Constants.COUNTRY_ID, vm.getStatistics().get(index).getUid());
        startActivity(intent);
    }

    // see setFocusChanged note on editText View
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

}