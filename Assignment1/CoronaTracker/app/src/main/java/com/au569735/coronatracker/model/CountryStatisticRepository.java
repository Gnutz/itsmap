package com.au569735.coronatracker.model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.au569735.coronatracker.database.CountryStatDatabase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CountryStatisticRepository {




    CountryStatDatabase database;
    ExecutorService executorService;
    LiveData<List<CountryStatistic>> statistics;

    CountryStatisticRepository instance;

    public static CountryStatisticRepository getInstance(final Application application){
        if(i == null){
            synchronized (CountryStatDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context_,
                            CountryStatDatabase.class, "country_stat_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomDatabase.Callback() {
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


    private CountryStatisticRepository(Application application) {
        database = CountryStatDatabase.getInstance(application.getApplicationContext());
        executorService = Executors.newSingleThreadExecutor();
        statistics = database.CountryStatisticDao().getAll();
    }

    public LiveData<List<CountryStatistic>> getStatistics() { return statistics; }

    public CountryStatistic getStatisticAsynch(final int uid){
        Future<CountryStatistic> cs = executorService.submit(new Callable<CountryStatistic>() {
            @Override
            public CountryStatistic call() {
                return database.CountryStatisticDao().findStatistic(uid);
            }
        });

        try {
            return cs.get();
        } catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return null;
    }

    public CountryStatistic getStatisticAsynch(final String country){
        Future<CountryStatistic> cs = executorService.submit(new Callable<CountryStatistic>() {
            @Override
            public CountryStatistic call() {
                return database.CountryStatisticDao().findStatistic(country);
            }
        });

        try {
            return cs.get();
        } catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return null;
    }

    public void addStatisticAsynch(final CountryStatistic statistic){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                database.CountryStatisticDao().addStatistic(statistic);
            }
        });
    }

    public void updateStatisticAsynch(final CountryStatistic statistic){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                database.CountryStatisticDao().updateStatistic(statistic);
            }
        });
    }

    public void deleteStatisticAsynch(final CountryStatistic statistic){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                database.CountryStatisticDao().deleteStatic(statistic);
            }
        });
    }


}

