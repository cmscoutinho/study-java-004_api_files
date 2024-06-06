package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Title;
import br.com.alura.screenmatch.model.TitleOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class MainWithSearch {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which movie do you want to search?: ");
        var query = scanner.next();

        String queryURL = "http://www.omdbapi.com/?t=" + query + "&apikey=337e4e55";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(queryURL))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
//        Title myTitle = gson.fromJson(json, Title.class);
        TitleOmdb myOmdbTitle = gson.fromJson(json, TitleOmdb.class);
        System.out.println(myOmdbTitle);
        Title myTitle = new Title(myOmdbTitle);
    }
}
