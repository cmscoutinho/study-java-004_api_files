package br.com.viacepchallenge.main;

import br.com.viacepchallenge.controller.CepSearch;
import br.com.viacepchallenge.controller.FileSaver;
import br.com.viacepchallenge.model.Address;
import br.com.viacepchallenge.view.Menu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String query;
        List<String> queriesJsonList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Menu menu = new Menu();
        menu.welcome();

        do {
            query = menu.read();
            if (query != null) {
                Address address = CepSearch.checkCEP(query);
                if (address != null) {
                    System.out.println(address);
                    queriesJsonList.add(gson.toJson(address));
                }
            }
        } while (query != null);

        try {
            FileSaver.saveFile(queriesJsonList);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Thanks for using ViaCEP Challenge!");
    }
}
