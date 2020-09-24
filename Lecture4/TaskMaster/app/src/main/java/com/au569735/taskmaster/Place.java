package com.au569735.taskmaster;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "places")
public class Place {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;

    public Place(){

    }

    public Place(String name){
        this.name = name;
    }

    public Place(long id, String name){
        this(name);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
