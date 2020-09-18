package com.gnutzlabs.oneapptorulethemall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements SearchDialogFragment.SearchDialogListener , ActivityMetaDataAdapter.IActivityMetaDataItemClickedListener {

    TextView txtHeading;
    RecyclerView rcvList;
    ActivityMetaDataAdapter adapter;

    ArrayList<ActivityMetaData> activites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ActivityMetaDataAdapter(this);
        rcvList = findViewById(R.id.rcvActivities);
        rcvList.setLayoutManager(new LinearLayoutManager(this));
        rcvList.setAdapter(adapter);
        
        createData();
        adapter.updateActivitiesList(activites);

    }

    private void createData() {
        activites = new ArrayList<ActivityMetaData>();
        activites.add(new ActivityMetaData(1, 1, "Picker", "This activity lets you select and return a number"));
        activites.add(new ActivityMetaData(2, 2, "Edit", "This activity input a range of strings and returns the collection"));
        activites.add(new ActivityMetaData(3, 3, "Slider", "Lets you select and returns the background rbg value"));
    }

    private void launchSlidersActivity() {
        startActivityForResult(new Intent(getApplicationContext(), SlidersActivity.class), Constants.SLIDERS_REQUEST);
    }

    private void launchEditActivity() {
        startActivityForResult(new Intent(getApplicationContext(), EditTextActivity.class), Constants.EDIT_TEXT_REQUEST);
    }

    void launchPickerActivity(){
        startActivityForResult(new Intent(this, PickerActivity.class), Constants.PICKER_REQUST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miSearch:
                launchSearchDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void launchSearchDialog() {
        SearchDialogFragment searchDialog = new SearchDialogFragment();
        searchDialog.show(getSupportFragmentManager(), "search dialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case Constants.PICKER_REQUST:
                    int result = data.getIntExtra(Constants.PICKER_RESULT, 0);
                    makeToast("Result from PickerActivity is: " + result);
                    break;
                case Constants.EDIT_TEXT_REQUEST:
                    ArrayList<String>  editValues = data.getStringArrayListExtra(Constants.EDIT_RESULT);
                    String editToast = "Result from EditActivity is: ";
                    for (String editValue : editValues) {
                        editToast += editValue + ", ";
                    }
                    makeToast(editToast);
                    break;
                case Constants.SLIDERS_REQUEST:
                    String rgbResult = data.getStringExtra(Constants.SLIDERS_RESULT);
                    makeToast("Result from SlidersActivity is:" + rgbResult);
                    break;

        }
        else makeToast("The chosen Activity was cancelled");

    }

    void makeToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void submitSearch(String searchTerm) {
        makeToast("You made a search for:" + searchTerm);
    }

    @Override
    public void onActivityMetadataClicked(int index) {
        switch (index){
            case 0:
                launchPickerActivity();
                break;
            case 1:
                launchEditActivity();
                break;
            case 2:
                launchSlidersActivity();
                break;
        }
    }
}

