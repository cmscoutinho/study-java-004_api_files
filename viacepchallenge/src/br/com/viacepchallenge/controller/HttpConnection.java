package br.com.viacepchallenge.controller;

import br.com.viacepchallenge.exception.InvalidCepException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpConnection {
    private final String BASE_URL = "http://viacep.com.br/ws/$query/json/";
    private String fullURL;

    HttpRequest request;
    HttpResponse response;
    HttpClient client;

    public HttpConnection(String query) {

        if (query.length() != 8) {
            throw new InvalidCepException("The CEP address must have 8 digits!");
        }

        try {
            fullURL = BASE_URL.replace("$query", query);

            request = HttpRequest.newBuilder().uri(URI.create(fullURL)).build();
            client = HttpClient.newHttpClient();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("Invalid query! Try again! Error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Connection to the remote host failed! Error: " + e.getMessage());
        }
    }

    public String getJson() {
        return response.body().toString();
    }

}
