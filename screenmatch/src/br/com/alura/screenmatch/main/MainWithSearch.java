package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Title;
import br.com.alura.screenmatch.model.TitleOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainWithSearch {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String query = "";
        List<Title> titles = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true) {

            System.out.print("Which movie do you want to search?: ");
            query = scanner.nextLine();

            if (query.equalsIgnoreCase("sair")) {
                break;
            } else {
                String queryURL = "http://www.omdbapi.com/?t=" + URLEncoder.encode(query, "UTF-8") + "&apikey=337e4e55";
                try {
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(queryURL)).build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    String json = response.body();
                    System.out.println(json);

//        Title myTitle = gson.fromJson(json, Title.class);
                    TitleOmdb myOmdbTitle = gson.fromJson(json, TitleOmdb.class);
                    System.out.println(myOmdbTitle);
//        try {
                    Title myTitle = new Title(myOmdbTitle);
                    System.out.println(myTitle);
                    System.out.println("Title converted!");
                    titles.add(myTitle);

                } catch (NumberFormatException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error! Please check the URI.");
                    System.out.println("Detailed error: " + e.getMessage());
                }
            }
        }
        System.out.println(titles);

        FileWriter writer = new FileWriter("movies.json");
        writer.write(gson.toJson(titles));
        writer.close();

        System.out.println("The program ended succesfully!");
    }
}
