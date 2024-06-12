package br.com.viacepchallenge.controller;

import br.com.viacepchallenge.model.Address;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FileSaver {

    private static final String fileName = "queries.json";
    private static final String filePath = "resources/" + fileName;

    public static void saveFile(List<String> queriesJsonList) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);

        writer.write("[");
        for (int i = 0; i < queriesJsonList.size(); i++) {
            writer.write(queriesJsonList.get(i));
            if (i < queriesJsonList.size()-1) {
                writer.write(",");
            }
        }
        writer.write("]");
        writer.close();
    }
}
