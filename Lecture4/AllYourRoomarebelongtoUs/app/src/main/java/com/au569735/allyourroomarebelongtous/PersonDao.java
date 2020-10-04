package com.au569735.allyourroomarebelongtous;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("SELECT * from person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE pid IN (:peopleIds)")
    List<Person> loadAllByIds(int[] peopleIds);

    @Query("SELECT * FROM person WHERE firstName LIKE :first AND " +
            "lastName LIKE :last LIMIT 1")
    Person findByName(String first, String last);

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);
}
