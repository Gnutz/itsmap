package com.au569735.coronatracker.viewmodels;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.au569735.coronatracker.model.CountryStatistic;

public class CountryStatisticViewModel extends ViewModel {

    MutableLiveData<CountryStatistic> countryStatistic;

    public LiveData<CountryStatistic> getCountryStatisticLiveData() {
        if (countryStatistic == null){
            countryStatistic = new MutableLiveData<CountryStatistic>();
        }
        return countryStatistic;
    }

    public CountryStatistic getCountryStatistic(){
        return getCountryStatisticLiveData().getValue();
    }

    public void updateCountryStatistic(CountryStatistic countryStatistic) {
        if (!countryStatistic.equals(getCountryStatistic()))
            this.countryStatistic.setValue(countryStatistic);
    }


}
