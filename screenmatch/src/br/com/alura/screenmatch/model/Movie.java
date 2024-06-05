package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.calculations.Ratable;

public class Movie extends Title implements Ratable {
    private String director;

    public Movie(String name, int releaseYear) {
        super(name, releaseYear);
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public int getRating() {
        return (int) getMean() / 2;
    }

    @Override
    public String toString() {
        return "Movie: " + getName() + " (" + getReleaseYear() + ")";
    }
}
