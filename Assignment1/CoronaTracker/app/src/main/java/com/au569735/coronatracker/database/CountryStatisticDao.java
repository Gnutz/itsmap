package com.au569735.coronatracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.au569735.coronatracker.model.CountryStatistic;

import java.util.List;

//TODO REFERENCE ROOM demo

@Dao
public interface CountryStatisticDao {

    @Query("SELECT * FROM countrystatistic")
    LiveData<List<CountryStatistic>> getAll();

    @Query("SELECT * FROM countrystatistic WHERE uid LIKE :uid LIMIT 1")
    CountryStatistic findStatictic(int uid);

    @Query("SELECT * FROM countrystatistic WHERE country LIKE :country LIMIT 1")
    CountryStatistic findStatitic(String country);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addStatistic(CountryStatistic stats);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(CountryStatistic... stats);

    @Update
    void updateStatistic(CountryStatistic stats);

    @Delete
    void deleteStatic(CountryStatistic stats);

    @Query("DELETE FROM countrystatistic")
    void deleteAll();
}
