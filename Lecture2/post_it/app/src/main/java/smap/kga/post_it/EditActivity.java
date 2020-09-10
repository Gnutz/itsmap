package smap.kga.post_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText txtEdit;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txtEdit = findViewById(R.id.txtInput);
        btnOk  = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultIsOk();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultIsCancel();
            }
        });
    }

    void resultIsOk(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("returnedText", txtEdit.getText().toString());
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    void resultIsCancel(){
        setResult(RESULT_CANCELED);
        finish();
    }
}