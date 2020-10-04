package com.au569735.coronatracker.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.au569735.coronatracker.model.CountryStatistic;

public class CountryStatisticViewModel extends ViewModel {

    MutableLiveData<CountryStatistic> countryStatistic;

    public LiveData<CountryStatistic> getCountryStatistic() {
        if (countryStatistic == null){
            countryStatistic = new MutableLiveData<CountryStatistic>();
        }
        return countryStatistic;
    }

    public void updateCountryStatistic(CountryStatistic countryStatistic) {
        this.countryStatistic.setValue(countryStatistic);
    }


}
