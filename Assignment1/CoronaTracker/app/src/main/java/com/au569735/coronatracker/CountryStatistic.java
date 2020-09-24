package com.au569735.coronatracker;

import java.io.Serializable;

public class CountryStatistic implements Serializable {

    String Country;
    String CountryCode;
    int FlagIconId;
    int Cases;
    int Deaths;
    double Rating;
    String Note;

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
