package com.au569735.coronatracker;

import java.io.Serializable;

public class CountryCoronaStatistic implements Serializable {

    String Country;
    String CountryCode;
    int FlagIconId;
    int NumberOfCases;
    int NumberOfDeaths;
    double UserRating;
    String UserNote;

    public CountryCoronaStatistic(String country, String code, int iconId, int cases, int deaths, double rating, String note){
        Country = country;
        CountryCode = code;
        FlagIconId = iconId;
        NumberOfCases = cases;
        NumberOfDeaths = deaths;
        UserRating = rating;
        UserNote = note;

    }
}
