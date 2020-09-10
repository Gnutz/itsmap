package com.example.clickcounter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {

    private MutableLiveData<Integer> count;

    public  LiveData<Integer> getCount(){
        if (count == null){
            count = new MutableLiveData<Integer>();
            count.setValue(0);
        }
        return count;
    }

    public void CountUpByValue(int value) {
        count.setValue(count.getValue() + value);
    }
}
