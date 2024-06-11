package br.com.viacepchallenge.main;

import br.com.viacepchallenge.controller.CepSearch;
import br.com.viacepchallenge.model.Address;
import br.com.viacepchallenge.view.Menu;

public class Main {
    public static void main(String[] args) {

        String query;

        Menu menu = new Menu();
        menu.welcome();

        do {
            query = menu.read();
            if (query != null) {
                Address address = CepSearch.checkCEP(query);
                if (address != null) {
                    System.out.println(address);
                }
            }
        } while (query != null);
    }
}
