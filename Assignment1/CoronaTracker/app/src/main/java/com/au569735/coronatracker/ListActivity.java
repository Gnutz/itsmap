package com.au569735.coronatracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity
implements CountryCoronaStatisticAdapter.ICountryCoronaStatisticItemClickedListener {


    RecyclerView rcvCountryStats;
    Button btnExit;
    CountryCoronaStatisticAdapter adapter;

    ArrayList<CountryCoronaStatistic> countryCoronaStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        adapter = new CountryCoronaStatisticAdapter(this);
        rcvCountryStats = findViewById(R.id.rcvStatistics);
        rcvCountryStats.setLayoutManager(new LinearLayoutManager(this));
        rcvCountryStats.setAdapter(adapter);

        createData();
        adapter.updateCStatistics(countryCoronaStatistics);


    }

    private void createData() {
        countryCoronaStatistics = new ArrayList<CountryCoronaStatistic>();
       countryCoronaStatistics.add( new CountryCoronaStatistic("Canada", "CA", R.drawable.ca, 142866, 9248, 0.0, ""));
       countryCoronaStatistics.add( new CountryCoronaStatistic("China", "CN", R.drawable.cn, 90294, 4736, 0.0, ""));
       countryCoronaStatistics.add( new CountryCoronaStatistic("Denmark","DK", R.drawable.dk, 21836,635, 0.0, ""));
       countryCoronaStatistics.add( new CountryCoronaStatistic("Germany", "DE",R.drawable.de,269048, 9376, 0.0, ""));
       countryCoronaStatistics.add( new CountryCoronaStatistic("Finland", "FI", R.drawable.fi,8799, 339, 0.0, ""));
       countryCoronaStatistics.add( new CountryCoronaStatistic("India", "IN", R.drawable.in, 5118253,83198, 0.0, ""));
       countryCoronaStatistics.add( new CountryCoronaStatistic("Japan", "JP",R.drawable.jp,77488, 1490, 0.0, ""));
       countryCoronaStatistics.add( new CountryCoronaStatistic("Norway", "NO", R.drawable.no,12644,266, 0.0, ""));
       countryCoronaStatistics.add( new CountryCoronaStatistic("Russian", "RU", R.drawable.ru,1081152,18996, 0.0, ""));
       countryCoronaStatistics.add( new CountryCoronaStatistic("Sweden","SE", R.drawable.se,87885,5864, 0.0, ""));
       countryCoronaStatistics.add( new CountryCoronaStatistic("USA", "US", R.drawable.us ,6674411,197633, 0.0, ""));
    }

    @Override
    public void onStatItemClicked(int index) {
        Intent intent = new Intent(getApplicationContext(), CountryDetailsActivity.class);
        intent.putExtra(Constants.STAT_BLOCK, countryCoronaStatistics.get(index));
        startActivity(intent);
    }
}