package com.au569735.coronatracker.model;

import java.io.Serializable;

public class CountryStatistic implements Serializable {

    public String Country;
    public String CountryCode;
    public int FlagIconId;
    public int Cases;
    public int Deaths;
    public double Rating;
    public String Note;

    public CountryStatistic(String country, String code, int iconId, int cases, int deaths, double rating, String note){
        Country = country;
        CountryCode = code;
        FlagIconId = iconId;
        Cases = cases;
        Deaths = deaths;
        Rating = rating;
        Note = note;

    }
}
