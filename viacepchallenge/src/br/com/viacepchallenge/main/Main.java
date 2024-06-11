package br.com.viacepchallenge.main;

import br.com.viacepchallenge.controller.HttpConnection;
import br.com.viacepchallenge.model.Address;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String query = "67013710";

        HttpConnection connection = new HttpConnection(query);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        Address address = gson.fromJson(connection.getJson(), Address.class);
        System.out.println(address);
    }
}
