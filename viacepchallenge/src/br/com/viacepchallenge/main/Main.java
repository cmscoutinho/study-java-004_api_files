package br.com.viacepchallenge.main;

import br.com.viacepchallenge.controller.HttpConnection;

import java.io.IOException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

        String query = "67013710";

        HttpConnection connection = null;
        try {
            connection = new HttpConnection(query);
        } catch (IOException e) {
            System.out.println("Invalid query! Try again! Error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Connection to the remote host failed! Error: " + e.getMessage());
        }

        System.out.println(connection.getJson());
    }
}
