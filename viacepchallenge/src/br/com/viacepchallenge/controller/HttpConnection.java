package br.com.viacepchallenge.controller;

import br.com.viacepchallenge.exception.InvalidCepException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpConnection {
    private String baseURL = "http://viacep.com.br/ws/$query/json/";
    private String query;

    HttpRequest request;
    HttpResponse response;
    HttpClient client;

    public HttpConnection(String query) throws IOException, InterruptedException {
        this.query = query.replace("-", "");

        if (this.query.length() != 8) {
            throw new InvalidCepException("The CEP address must have 8 digits!");
        }

        baseURL = baseURL.replace("$query", query);
        System.out.println(baseURL);

        request = HttpRequest
                .newBuilder()
                .uri(URI.create(baseURL))
                .build();
        client = HttpClient.newHttpClient();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String getJson() {
        return response.body().toString();
    }
}
