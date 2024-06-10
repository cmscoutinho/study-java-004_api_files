package ex_04_09.ex_02.main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ex_04_09.ex_02.model.Title;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Title title = new Title("Gladiator", 2001);

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();

        try {
            FileWriter fileWriter = new FileWriter("src/ex_04_09/ex_02/res/title.json");
            fileWriter.write(gson.toJson(title));
            fileWriter.close();
            System.out.println("File saved!");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }

    }
}
