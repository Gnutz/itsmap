package com.au569735.allyourroomarebelongtous;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {

    @PrimaryKey
    public int pid;

    public String firstName;
    public String lastName;
    public String email;
}
