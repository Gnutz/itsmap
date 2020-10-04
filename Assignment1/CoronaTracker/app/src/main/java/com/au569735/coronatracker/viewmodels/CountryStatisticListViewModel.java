package com.au569735.coronatracker.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.au569735.coronatracker.utils.CSVReader;
import com.au569735.coronatracker.R;
import com.au569735.coronatracker.model.CountryStatistic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CountryStatisticListViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<CountryStatistic>> countryStatistics;
    private Application application;

    public CountryStatisticListViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<ArrayList<CountryStatistic>> getStatisticsLiveData() {
        if (countryStatistics == null) {
            countryStatistics = new MutableLiveData<ArrayList<CountryStatistic>>();
            ArrayList<CountryStatistic> statistics = LoadData();
            countryStatistics.setValue(statistics);
        }
        return countryStatistics;
    }

    public ArrayList<CountryStatistic> getStatistics(){
        return getStatisticsLiveData().getValue();
    }

    void updateStatistics(ArrayList<CountryStatistic> newList){
        countryStatistics.setValue(newList);
    }

    public  void updateStatistic(CountryStatistic updatedStats){
        ArrayList<CountryStatistic> statistics = getStatistics();
        int index = getIndexByCountry(statistics, updatedStats.Country);
        statistics.set(index, updatedStats);
        updateStatistics(statistics);
    }

    private int getIndexByCountry(ArrayList<CountryStatistic> statistics, String country) {
        for (CountryStatistic stats : statistics) {
            if (stats.Country.toUpperCase().equals(country.toUpperCase())) {
                return statistics.indexOf(stats);
            }
        }

        return -1;
    }


    ArrayList<CountryStatistic> LoadData() {
        ArrayList<CountryStatistic> statistics = new ArrayList<>();

        InputStream inputStream = application.getResources().openRawResource(R.raw.corona_stats);
        CSVReader csvReader = new CSVReader(inputStream);
        List<String[]> statsList = csvReader.read();

        for (String[] statLine : statsList) {
            String country = statLine[0];
            String countryCode = statLine[1];
            int flagId = application.getResources().getIdentifier(countryCode.toLowerCase(),
                    "drawable", getApplication().getPackageName());
            int cases = Integer.parseInt(statLine[2]);
            int deaths = Integer.parseInt(statLine[3]);

            statistics.add(new CountryStatistic(country, countryCode, flagId, cases, deaths, 5.0, ""));
        }

        return statistics;
    }
}

