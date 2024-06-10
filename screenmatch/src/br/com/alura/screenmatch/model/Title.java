package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.exception.YearConversionException;
import com.google.gson.annotations.SerializedName;

public class Title implements Comparable<Title> {
    //    @SerializedName("Title") -> this annotation is used in the case of no Records usage
    private String name;
    //    @SerializedName("Year") -> this one too
    private int releaseYear;
    private boolean includedInSubscription;
    private double sumOfRatings;
    private int overallRating;
    private int lengthInMinutes;

    public Title(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public Title(TitleOmdb myOmdbTitle) {
        this.name = myOmdbTitle.title();
        if (myOmdbTitle.year().length() > 4) {
            throw new YearConversionException("Couldn't convert year because of its length greater than 4.");
        }
        this.releaseYear = Integer.valueOf(myOmdbTitle.year());
        this.lengthInMinutes = Integer.valueOf(myOmdbTitle.runtime().split(" ")[0]);
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public boolean isIncludedInSubscription() {
        return includedInSubscription;
    }

    public double getSumOfRatings() {
        return sumOfRatings;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSumOfRatings(double sumOfRatings) {
        this.sumOfRatings = sumOfRatings;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public void setIncludedInSubscription(boolean includedInSubscription) {
        this.includedInSubscription = includedInSubscription;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public void showInfo() {
        System.out.println("Movie name: " + name);
        System.out.println("Release year: " + releaseYear);
        System.out.println("Length in minutes: " + lengthInMinutes);
        System.out.println("Included in subscription: " + includedInSubscription);
    }

    public void rate(double score) {
        sumOfRatings += score;
        overallRating++;
    }

    public double getMean() {
        return sumOfRatings / overallRating;
    }

    @Override
    public int compareTo(Title otherTitle) {
        return this.getName().compareTo(otherTitle.getName());
    }

    @Override
    public String toString() {
        return name + " (" + releaseYear + ") : " + lengthInMinutes + " minutes";
    }
}
