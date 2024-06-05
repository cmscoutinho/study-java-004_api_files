package br.com.alura.screenmatch;

import br.com.alura.screenmatch.calculations.TimeCalculator;
import br.com.alura.screenmatch.calculations.RecommendationFilter;
import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.Movie;
import br.com.alura.screenmatch.model.Series;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Movie myMovie = new Movie("O Poderoso Chefão", 1970);
        myMovie.setLengthInMinutes(180);
        System.out.println("Movie length: " + myMovie.getLengthInMinutes());
        myMovie.setIncludedInSubscription(true);

        myMovie.showInfo();
        myMovie.rate(5);
        myMovie.rate(8);
        myMovie.rate(10);

//        System.out.println(meuFilme.somaDasAvaliacoes);
        System.out.println("Overall rating: " + myMovie.getOverallRating());
        System.out.println(myMovie.getMean());

        Series lost = new Series("Lost", 2000);
        lost.setSeasons(10);
        lost.setEpisodesPerSeason(10);
        lost.setMinutesPerEpisode(50);
        lost.showInfo();
        System.out.println("Series length: " + lost.getLengthInMinutes() + " minutes.");

        Movie otherMovie = new Movie("Avatar", 2023);
        otherMovie.setLengthInMinutes(200);

        TimeCalculator calculator = new TimeCalculator();
        calculator.include(myMovie);
        calculator.include(otherMovie);
        calculator.include(lost);
        System.out.println(calculator.getTotalTime());

        RecommendationFilter filter = new RecommendationFilter();
        filter.filter(myMovie);

        Episode episode = new Episode();
        episode.setNumber(1);
        episode.setSeries(lost);
        episode.setTotalViews(300);
        filter.filter(episode);

        var paulosMovie = new Movie("Dogville", 2003);
        paulosMovie.setLengthInMinutes(200);
        paulosMovie.rate(10);

        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(paulosMovie);
        movieList.add(myMovie);
        movieList.add(otherMovie);
        System.out.println("Length of list: " + movieList.size());
        System.out.println("First movie: " + movieList.get(0).getName());
        System.out.println(movieList);
        System.out.println("Movie's toString(): " + movieList.get(0).toString());

    }
}
