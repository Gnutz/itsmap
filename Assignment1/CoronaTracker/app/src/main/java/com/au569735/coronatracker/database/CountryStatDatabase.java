package com.au569735.coronatracker.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.au569735.coronatracker.R;
import com.au569735.coronatracker.model.CountryStatistic;
import com.au569735.coronatracker.utils.CSVReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TODO REFERENCE ROOM demo

@Database(entities = {CountryStatistic.class}, version = 1,  exportSchema = false)
public abstract class CountryStatDatabase extends RoomDatabase {

    public abstract CountryStatisticDao CountryStatisticDao();

    static Context context_;
    static CountryStatDatabase instance;

    public static CountryStatDatabase getInstance(final Context context){
        context_ = context.getApplicationContext();
        if(instance == null){
            synchronized (CountryStatDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context_,
                            CountryStatDatabase.class, "country_stat_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            instance.CountryStatisticDao().addAll(
                                                    CountryStatDatabase.LoadseedData()
                                            );
                                        }
                                    });
                                }
                            })
                            .build();
                }
            }
        }

        return instance;
    }

    private static CountryStatistic[] LoadseedData(){
        InputStream inputStream = context_.getResources().openRawResource(R.raw.corona_stats);
        CSVReader csvReader = new CSVReader(inputStream);
        List<String[]> statsList = csvReader.read();
        CountryStatistic[] statistics = new CountryStatistic[statsList.size()];

        int index = 0;
        for (String[] statLine : statsList) {
            String country = statLine[0];
            String countryCode = statLine[1];
            int flagId = context_.getResources().getIdentifier(countryCode.toLowerCase(),
                    "drawable", context_.getPackageName());
            int cases = Integer.parseInt(statLine[2]);
            int deaths = Integer.parseInt(statLine[3]);

            statistics[index] = new CountryStatistic(country, countryCode, flagId, cases, deaths, 5.0, "");
            index++;
        }

        return statistics;
    }
}
