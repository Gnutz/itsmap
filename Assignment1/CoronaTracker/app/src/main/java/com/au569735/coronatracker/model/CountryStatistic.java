package com.au569735.coronatracker.model;

import android.os.Build;

import java.io.Serializable;
import java.util.Objects;

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

    public CountryStatistic(CountryStatistic other){
        Country = other.Country;
        CountryCode = other.CountryCode;
        FlagIconId = other.FlagIconId;
        Cases = other.Cases;
        Deaths = other.Deaths;
        Rating = other.Rating;
        Note = other.Note;
    }

    //autogenerated equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryStatistic)) return false;
        CountryStatistic that = (CountryStatistic) o;
        return FlagIconId == that.FlagIconId &&
                Cases == that.Cases &&
                Deaths == that.Deaths &&
                Double.compare(that.Rating, Rating) == 0 &&
                Country.equals(that.Country) &&
                CountryCode.equals(that.CountryCode) &&
               Note.equals(that.Note);
    }

}
