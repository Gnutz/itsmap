package com.au569735.coronatracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.au569735.coronatracker.model.CountryStatistic;

//TODO REFERENCE ROOM demo

@Database(entities = {CountryStatistic.class}, version = 1,  exportSchema = false)
public abstract class CountryStatDatabase extends RoomDatabase {

    public abstract CountryStatisticDao CountryStatistic();

    static CountryStatDatabase instance;

    public static CountryStatDatabase getInstance(Context context){
        if(instance == null){
            synchronized (CountryStatDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            CountryStatDatabase.class, "country_stat_database")
                            .fallbackToDestructiveMigration()
                            //.addCallback()
                            .build();
                }
            }
        }

        return instance;
    }
}
