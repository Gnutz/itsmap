package smap.kga.post_it;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int EDIT_TEXT_REQUEST = 0;
    TextView txtHeader;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHeader = findViewById(R.id.txtHeader);
        btnEdit = findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    launchEditActivity();
            }
        });
    }

    void launchEditActivity(){
        startActivityForResult(
                new Intent(this, EditActivity.class),
                EDIT_TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == EDIT_TEXT_REQUEST){
            if (resultCode == RESULT_OK){
                String returnedText = data.getStringExtra("returnedText");
                txtHeader.setText(returnedText);
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}