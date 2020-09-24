package com.au569735.coronatracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class EditContryStatActivity extends AppCompatActivity {

    ImageView imgFlagIcon;
    TextView txtCountry, txtRating;
    SeekBar skbRating;
    EditText etxtNote;
    Button btnCancel, btnOk;

    CountryStatisticViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_country_stat);

        imgFlagIcon = findViewById(R.id.imgEditFlagIcon);
        txtCountry = findViewById(R.id.txtEditCountryName);
        txtRating = findViewById(R.id.txtEditUserRating);
        skbRating = findViewById(R.id.skbUserRating);

        vm = new ViewModelProvider(this).get(CountryStatisticViewModel.class);
        vm.getCountryStatistic().observe(this, new Observer<CountryStatistic>() {
            @Override
            public void onChanged(CountryStatistic countryStatistic) {
                updateUI(countryStatistic);
            }
        });

        Intent passedIntent = getIntent();
        CountryStatistic stats = (CountryStatistic) passedIntent.getSerializableExtra(Constants.STAT_BLOCK);
        vm.updateCountryStatistic(stats);

        skbRating = findViewById(R.id.skbUserRating);
        skbRating.setProgress((int) vm.getCountryStatistic().getValue().Rating *10);
        skbRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                CountryStatistic stats = vm.getCountryStatistic().getValue();
                stats.Rating = (float) progress/10;
                vm.updateCountryStatistic(stats);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        etxtNote = findViewById(R.id.editTxtUserNotes);

       etxtNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                CountryStatistic stats = vm.getCountryStatistic().getValue();
                stats.Note = s.toString();
                vm.updateCountryStatistic(stats);

            }
        });

        // hide keyboard when editText
        // #1: set OnFocusChangedListener for the input field
        // #2 Override the activity's DispatchTouchEvent method see below
        // snippets copied from https://stackoverflow.com/questions/4165414/how-to-hide-soft-keyboard-on-android-after-clicking-outside-edittext?page=2&tab=votes#tab-top
        //
        etxtNote.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

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
                Intent intent = new Intent(getApplication(), EditContryStatActivity.class);
                intent.putExtra(Constants.STAT_BLOCK, vm.getCountryStatistic().getValue());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void updateUI(CountryStatistic countryStatistic) {
        imgFlagIcon.setImageResource(countryStatistic.FlagIconId);
        txtCountry.setText(countryStatistic.Country);
        txtRating.setText(String.format("%.1f", countryStatistic.Rating));
    }


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