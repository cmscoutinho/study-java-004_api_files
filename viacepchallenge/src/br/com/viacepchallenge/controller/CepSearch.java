package br.com.viacepchallenge.controller;

import br.com.viacepchallenge.model.Address;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CepSearch {

    public static Address checkCEP(String query) {
        HttpConnection connection = new HttpConnection(query);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.fromJson(connection.getJson(), Address.class);
    }
}
