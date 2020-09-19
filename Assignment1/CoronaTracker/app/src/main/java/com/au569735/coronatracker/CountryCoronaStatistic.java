package com.au569735.coronatracker;

public class CountryCoronaStatistic {

    String Country;
    String CountryCode;
    int FlagIconId;
    int NumberOfCases;
    int NumberOfDeaths;
    double UserRating;
    String UserNote;

    public CountryCoronaStatistic(String country, String code, int iconId, int cases, double rating, String note){
        Country = country;
        CountryCode = code;
        FlagIconId = iconId;
        NumberOfCases = cases;
        NumberOfDeaths = cases;
        UserRating = rating;
        UserNote = note;

    }
}
