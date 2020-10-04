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
        if(getCountryStatisticLiveData().getValue() == null){
            // bit hacky solution I know
            countryStatistic.setValue(new CountryStatistic("", "", -1, 0, 0, 0.0, ""));
        }
        return new CountryStatistic(getCountryStatisticLiveData().getValue());
    }

    public void updateCountryStatistic(CountryStatistic countryStatistic) {
        if (!countryStatistic.equals( getCountryStatistic())) {
            this.countryStatistic.setValue(countryStatistic);
        }
    }


}
