package com.au569735.coronatracker.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.au569735.coronatracker.model.CountryStatisticRepository;
import com.au569735.coronatracker.utils.CSVReader;
import com.au569735.coronatracker.R;
import com.au569735.coronatracker.model.CountryStatistic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CountryStatisticListViewModel extends AndroidViewModel {

    private final CountryStatisticRepository repository;
    private LiveData<List<CountryStatistic>> countryStatistics;

    public CountryStatisticListViewModel(@NonNull Application application) {
        super(application);
        this.repository = CountryStatisticRepository.getInstance(application);
        countryStatistics = repository.getStatistics();
    }

    public LiveData<List<CountryStatistic>> getStatisticsLiveData() {
      return countryStatistics;
    }

    public List<CountryStatistic> getStatistics(){
        return getStatisticsLiveData().getValue();
    }

    public  void updateStatistic(final CountryStatistic updatedStats){
        repository.updateStatisticAsynch(updatedStats);
    }

    private void updateStatistics(List<CountryStatistic> newList){
        //countryStatistics.setValue(newList);
    }

    private int getIndexByCountry(List<CountryStatistic> statistics, String country) {
        //for (CountryStatistic stats : getStatistics()) {
        //    if (stats.getCountry().toUpperCase().equals(country.toUpperCase())) {
        //        return statistics.indexOf(stats);
        //    }
        //}

        return -1;
    }

}

