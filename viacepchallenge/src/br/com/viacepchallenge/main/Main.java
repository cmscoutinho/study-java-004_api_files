package br.com.viacepchallenge.main;

import br.com.viacepchallenge.controller.CepSearch;
import br.com.viacepchallenge.controller.FileSaver;
import br.com.viacepchallenge.model.Address;
import br.com.viacepchallenge.view.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String query;
        List<Address> queriesJsonList = new ArrayList<>();

        Menu menu = new Menu();
        menu.welcome();

        do {
            query = menu.read();
            if (query != null) {
                Address address = CepSearch.checkCEP(query);
                if (address != null) {
                    System.out.println(address);
                    queriesJsonList.add(address);
                    try {
                        FileSaver.saveFile(queriesJsonList);
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
        } while (query != null);

        System.out.println("Thanks for using ViaCEP Challenge!");
    }
}
