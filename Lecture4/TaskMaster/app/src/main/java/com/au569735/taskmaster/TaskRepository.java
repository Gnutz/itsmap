package com.au569735.taskmaster;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application){
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao  = database.taskDao();
        allTasks = (LiveData<List<Task>>) taskDao.getAll();

    }

    public void insert(Task task){
        new InsertTaskAsyncTask(taskDao).execute(task);

    }

    public  void update(Task task){
        new UpdateTaskAsynTask(taskDao).execute(task);

    }

    public void delete(Task task){
        new DeleteTaskAsyncTask(taskDao).execute(task);
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao taskDao;

        private InsertTaskAsyncTask(TaskDao  taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks)
        {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsynTask extends AsyncTask<Task, Void, Void>{

        private TaskDao taskDao;

        private UpdateTaskAsynTask(TaskDao  taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks)
        {
            taskDao.insert(tasks[0]);
            return null;
        }
    }
    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao taskDao;

        private DeleteTaskAsyncTask(TaskDao  taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks)
        {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

}
