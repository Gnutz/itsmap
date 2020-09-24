package com.au569735.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lstTasks;
    private List<TaskViewModel> tasksFromDb;
    private TaskAdaptor adaptor;
    private Button btnAddTask;
    private EditText edtTaskName, edtTaskPlace;
    TaskDatabase Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Widgets and UI
        lstTasks = findViewById(R.id.listView);
        edtTaskName =  findViewById(R.id.edtTaskName);
        edtTaskPlace = findViewById(R.id.edtTaskPlace);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        Db = Room.databaseBuilder(getApplicationContext(),
                TaskViewModel.class, "tasks").build();

        updateTaskList();



        adaptor = new TaskAdaptor(this, tasksFromDb);
        lstTasks.setAdapter(adaptor);
        lstTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TaskViewModel t = tasksFromDb.get(position);
                if(t!=null){
                    //not the most efficient way, but we need to delete item in DB and update our listview
                    getDatabase().deleteTask(t.getId());
                    updateTaskList();
                    adaptor.setTasks(tasksFromDb);
                    adaptor.notifyDataSetChanged();
                }
            }
        });


    }

    private void updateTaskList() {
    }

    private void addTask() {
        String task = edtTaskName.getText().toString();
        String place = edtTaskPlace.getText().toString();
        if(task==null || task.equals("")){
            Toast.makeText(MainActivity.this, "Please enter a task name", Toast.LENGTH_SHORT).show();
        } else if(place==null || place.equals("")){
            Toast.makeText(MainActivity.this, "Please enter a task place name", Toast.LENGTH_SHORT).show();
        } else {

            //Enter int db

            //update UI
            edtTaskName.setText("");
            edtTaskPlace.setText("");
            adaptor.setTasks(tasksFromDb);
            ((BaseAdapter)lstTasks.getAdapter()).notifyDataSetChanged();

        }
    }

    }
}