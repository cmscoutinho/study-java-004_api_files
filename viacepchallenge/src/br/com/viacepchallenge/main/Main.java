package br.com.viacepchallenge.main;

import br.com.viacepchallenge.controller.HttpConnection;
import br.com.viacepchallenge.model.Address;
import br.com.viacepchallenge.view.Menu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String query;

        Menu menu = new Menu();
        menu.welcome();

        do {
            query = menu.read();
            if (query != null) {
                HttpConnection connection = new HttpConnection(query);
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
                Address address = gson.fromJson(connection.getJson(), Address.class);
                System.out.println(address);
            }
        } while (query != null);
    }
}
