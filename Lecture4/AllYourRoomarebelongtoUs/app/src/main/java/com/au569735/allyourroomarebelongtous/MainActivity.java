package com.au569735.allyourroomarebelongtous;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtPeople;
    EditText txtFirstName, txtLastName;
    Button btnAdd
    PersonDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room
                .databaseBuilder(getApplicationContext(), PersonDatabase.class, "person-database")
                .build();

        txtPeople = findViewById(R.id.txtPeople);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        btnAdd = findViewById(R.id.btnAdd);




    }
}
