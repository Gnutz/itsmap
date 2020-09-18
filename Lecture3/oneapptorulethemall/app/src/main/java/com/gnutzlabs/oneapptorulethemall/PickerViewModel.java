package com.gnutzlabs.oneapptorulethemall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PickerViewModel extends ViewModel {

    MutableLiveData<Integer> pickerValue;

    public LiveData<Integer> getValue(){
        if(pickerValue == null){
            pickerValue =  new MutableLiveData<Integer>();
            pickerValue.setValue(0);
        }

        return pickerValue;
    }

    public void setPickerValue(int value) {
        pickerValue.setValue(value);
    }
}
