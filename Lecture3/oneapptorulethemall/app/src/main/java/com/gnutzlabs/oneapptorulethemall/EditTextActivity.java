package com.gnutzlabs.oneapptorulethemall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EditTextActivity extends AppCompatActivity {

    EditText edtxtPlain, edtxtEmail, edtxtNumber, edtxtPassword;
    Button btnOk_edit, btnCancel_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        edtxtPlain = findViewById(R.id.edtxtPlain);
        edtxtEmail = findViewById(R.id.edtxtEmail);
        edtxtNumber = findViewById(R.id.edtxtNumber);
        edtxtPassword = findViewById(R.id.edtxtPassword);

        btnCancel_edit = findViewById(R.id.btnCancel_edit);
        btnCancel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOk_edit = findViewById(R.id.btnOk_edit);
        btnOk_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> editValues = new ArrayList<String>();
                editValues.add(edtxtPlain.getText().toString());
                editValues.add(edtxtEmail.getText().toString());
                editValues.add(edtxtNumber.getText().toString());
                editValues.add(edtxtPassword.getText().toString());
                setResult(RESULT_OK, new Intent().putExtra(Constants.EDIT_RESULT, editValues));
                finish();
            }
        });



    }
}