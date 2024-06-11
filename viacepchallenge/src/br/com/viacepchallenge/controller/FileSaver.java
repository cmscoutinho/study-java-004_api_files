package br.com.viacepchallenge.controller;

import br.com.viacepchallenge.model.Address;

import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

    private static final String fileName = "queries.txt";
    private static final String filePath = "resources/" + fileName;

    public static void saveFile(Address address) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(address.toString());
        writer.close();
    }
}
