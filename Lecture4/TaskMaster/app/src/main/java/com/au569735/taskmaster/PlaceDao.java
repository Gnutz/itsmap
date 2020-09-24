package com.au569735.taskmaster;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by kasper on 24/04/16.
 */

@Dao
public interface PlaceDao{

    @Query("SELECT * FROM tasks")
    List<Place> getPlaces();

    @Query("SELECT * FROM places WHERE name LIKE name LIMIT 1")
    Place findByName(String name);

    @Insert
    void insert(Place place);

    @Update
    void update(Place place);

    @Delete
    void delete(Place place);
}
