package br.com.viacepchallenge.view;

import java.util.Scanner;

public class Menu {

    public void welcome() {
        System.out.println("Welcome to the ViaCEP API interface!");
    }

    public String read() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Which CEP (ZIP) code do you want to search? (\"0\" to exit): ");
        String query = scanner.nextLine().replace("-", "");

        if (query.equals("0")) {
            return null;
        } else {
            return query;
        }
    }
}
