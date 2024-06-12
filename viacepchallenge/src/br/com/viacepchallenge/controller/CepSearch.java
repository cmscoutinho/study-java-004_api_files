package br.com.viacepchallenge.controller;

import br.com.viacepchallenge.exception.InvalidCepException;
import br.com.viacepchallenge.model.Address;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CepSearch {

    public static Address checkCEP(String query) {
        try {
            HttpConnection connection = new HttpConnection(query);
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            return gson.fromJson(connection.getJson(), Address.class);
        } catch (InvalidCepException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
