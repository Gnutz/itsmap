package com.au569735.coronatracker.viewmodels;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.au569735.coronatracker.model.CountryStatistic;
import com.au569735.coronatracker.model.CountryStatisticRepository;

public class CountryStatisticEditViewModel extends AndroidViewModel {

    MutableLiveData<CountryStatistic> countryStatistic;
    CountryStatisticRepository repository;

    public CountryStatisticEditViewModel(@NonNull Application application) {
        super(application);

        repository = CountryStatisticRepository.getInstance(application);
    }



    public LiveData<CountryStatistic> getCountryStatisticLiveData() {
        if (countryStatistic == null){
            countryStatistic = new MutableLiveData<CountryStatistic>();
        }
        return countryStatistic;
    }

    public CountryStatistic getCountryStatistic(){
        if(getCountryStatisticLiveData().getValue() == null){
            // bit hacky solution I know
           return  null;
        }
        return new CountryStatistic(getCountryStatisticLiveData().getValue());
    }

    public void updateCountryStatistic(CountryStatistic countryStatistic) {
        CountryStatistic currentStats = getCountryStatistic();
        if (!countryStatistic.equals(currentStats)) {
            this.countryStatistic.setValue(countryStatistic);
        }
    }


    public void selectStatisticById(int statisticId) {
        CountryStatistic selectedStats = repository.getStatisticAsynch(statisticId);
        this.countryStatistic.setValue(selectedStats);
    }
}
