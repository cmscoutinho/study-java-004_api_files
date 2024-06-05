package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Movie;
import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.model.Title;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainWithLists {
    public static void main(String[] args) {
        Movie myMovie = new Movie("O Poderoso Chefão", 1970);
        myMovie.rate(9);
        Movie otherMovie = new Movie("Avatar", 2023);
        otherMovie.rate(6);
        var paulosMovie = new Movie("Dogville", 2003);
        paulosMovie.rate(10);
        Series lost = new Series("Lost", 2000);

        List<Title> watched = new ArrayList<>();
        watched.add(paulosMovie);
        watched.add(myMovie);
        watched.add(otherMovie);
        watched.add(lost);

        for (Title item: watched) {
            System.out.println(item.getName());
            if (item instanceof Movie movie && movie.getRating() > 2) {
                System.out.println("Rating: " + movie.getRating());
            }
        }

        List<String> searchByArtist = new ArrayList<>();
        searchByArtist.add("Adam Sandler");
        searchByArtist.add("Paulo");
        searchByArtist.add("Jacqueline");
        System.out.println(searchByArtist);

        Collections.sort(searchByArtist);
        System.out.println("After sorting:");
        System.out.println(searchByArtist);

        System.out.println("List of sorted titles:");
        Collections.sort(watched);
        System.out.println(watched);
        watched.sort(Comparator.comparing(Title::getReleaseYear));
        System.out.println("Sorted by year:");
        System.out.println(watched);
    }
}
