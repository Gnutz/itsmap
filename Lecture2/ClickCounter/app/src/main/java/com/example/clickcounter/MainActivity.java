package com.example.clickcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String COUNT_KEY = "count_key";
    CounterViewModel vm;
    TextView txtCounter;
    Button btnCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vm = new ViewModelProvider(this).get(CounterViewModel.class);
        vm.getCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                updateUI(integer);
            }
        });

        //references for views
        txtCounter = findViewById(R.id.txtCounter);
        btnCounter = findViewById(R.id.button);

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.CountUpByValue(1);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //updateUI();
    }

    /*@Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(COUNT_KEY, count);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        count = savedInstanceState.getInt(COUNT_KEY);
        super.onRestoreInstanceState(savedInstanceState);
    }*/

    @Override
    protected void onStop() {
        super.onStop();
    }

    void updateUI(int newCount){
        txtCounter.setText("Count: " + newCount);
    }
}