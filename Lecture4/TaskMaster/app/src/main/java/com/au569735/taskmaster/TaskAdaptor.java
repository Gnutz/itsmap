package com.au569735.taskmaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskAdaptor extends BaseAdapter {

    private List<TaskViewModel> tasks;
    private Context context;

    TaskViewModel tempTask;

    public TaskAdaptor(Context context, List<TaskViewModel> list) {
        this.context = context;
        tasks = list;
    }

    @Override
    public int getCount() {
        if (tasks == null) {
            return 0;
        }
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        if (tasks != null && tasks.size() > position) {
            return tasks.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public List<TaskViewModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskViewModel> tasks) {
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.task_list_item, null);
        }

        if (tasks != null && tasks.size() > position) {
            tempTask = tasks.get(position);

            TextView t = (TextView) convertView.findViewById(R.id.txtTaskName);
            t.setText(tempTask.getName());
            TextView p = (TextView) convertView.findViewById(R.id.txtLocation);
            p.setText(tempTask.getPlace().getName());
            return convertView;
        }
        return null;
    }
}