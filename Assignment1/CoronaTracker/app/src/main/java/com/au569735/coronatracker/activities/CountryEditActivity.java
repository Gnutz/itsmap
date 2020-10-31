package com.au569735.coronatracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.au569735.coronatracker.R;
import com.au569735.coronatracker.model.CountryStatistic;
import com.au569735.coronatracker.utils.Constants;
import com.au569735.coronatracker.viewmodels.CountryStatisticEditViewModel;

public class CountryEditActivity extends AppCompatActivity {

    ImageView imgFlagIcon;
    TextView txtCountry, txtRating;
    SeekBar skbRating;
    EditText editTxtNote;
    Button btnCancel, btnOk;
    CountryStatisticEditViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_country_stat);

        imgFlagIcon = findViewById(R.id.imgEditFlagIcon);
        txtCountry = findViewById(R.id.txtEditCountryName);
        txtRating = findViewById(R.id.txtEditUserRating);
        editTxtNote = findViewById(R.id.editTxtUserNotes);

        vm = new ViewModelProvider(this).get(CountryStatisticEditViewModel.class);
        vm.getCountryStatisticLiveData().observe(this, new Observer<CountryStatistic>() {
            @Override
            public void onChanged(CountryStatistic countryStatistic) {
                updateUI(countryStatistic);
            }
        });

        if(vm.getCountryStatistic() == null ) {
            Intent passedIntent = getIntent();
            int statisticId = passedIntent.getIntExtra(Constants.COUNTRY_ID, -1);
            vm.selectStatisticById(statisticId);
        }

        skbRating = findViewById(R.id.skbUserRating);
        skbRating.setProgress((int) vm.getCountryStatistic().getRating() * 10);
        skbRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                CountryStatistic stats = vm.getCountryStatistic();
                stats.setRating((float) progress/10);
                vm.updateCountryStatistic(stats);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // hide keyboard when editText loses focus
        // #1: set OnFocusChangedListener for the input field
        // #2 Override the activity's DispatchTouchEvent method see below
        // snippets copied from https://stackoverflow.com/questions/4165414/how-to-hide-soft-keyboard-on-android-after-clicking-outside-edittext?page=2&tab=votes#tab-top
        editTxtNote.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    CountryStatistic stats = vm.getCountryStatistic();
                    stats.setNote(editTxtNote.getText().toString());
                    vm.updateCountryStatistic(stats);
                }
            }
        });


        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.updateCountryStatisticOnDatabase(vm.getCountryStatistic());
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    private void updateUI(CountryStatistic countryStatistic) {
        imgFlagIcon.setImageResource(countryStatistic.getFlagIconId());
        txtCountry.setText(countryStatistic.getCountry());
        txtRating.setText(String.format("%.1f", countryStatistic.getRating()));
        editTxtNote.setText(countryStatistic.getNote());
    }

    // see setFocusChanged note on editText View
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

}