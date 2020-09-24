package com.au569735.personpersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText txtFirstName, txtLastName, txtAge, txtPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFirstName = findViewById(R.id.edtTxtFirstName);
        txtLastName = findViewById(R.id.edtTxtLastName);
        txtAge = findViewById(R.id.edtTxtAge);
        txtPhoneNumber = findViewById(R.id.edtTxtPhoneNumber);

    }

    @Override
    protected void onResume() {
        super.onResume();

        retrieveInputs();
    }

    @Override
    protected void onStop() {
        super.onStop();
        storeInputs();
    }

    void storeInputs(){

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.firstName), txtFirstName.getText().toString());
        editor.putString(getString(R.string.lastName), txtLastName.getText().toString());
        editor.putString(getString(R.string.age), txtAge.getText().toString());
        editor.putString(getString(R.string.phoneNumber), txtPhoneNumber.getText().toString());
        editor.apply();
    }

    void retrieveInputs(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        txtFirstName.setText(sharedPref.getString(getString(R.string.firstName), ""));
        txtLastName.setText(sharedPref.getString(getString(R.string.lastName), ""));
        txtAge.setText(sharedPref.getString(getString(R.string.age), ""));
        txtPhoneNumber.setText(sharedPref.getString(getString(R.string.phoneNumber), ""));
    }

}