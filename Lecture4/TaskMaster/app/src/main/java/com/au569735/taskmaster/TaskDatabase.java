package com.au569735.taskmaster;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class, Place.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    private static  TaskDatabase instance;
    public  abstract TaskDao taskDao();
    public abstract  PlaceDao placeDao();

    public static synchronized TaskDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class, "tas_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PoplulateDbAsyncTask(instance).execute();
        }

        private  static  class PoplulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

            private TaskDao taskDao;

            private  PoplulateDbAsyncTask(TaskDatabase db){
                taskDao = db.taskDao();
            }

            @Override
            protected Void doInBackground(Void... voids){
                taskDao.insert(new Task("SMAP Assignment", new Place("@home"), 0, "sometime"));
                return null;
            }
        }
    }
}
