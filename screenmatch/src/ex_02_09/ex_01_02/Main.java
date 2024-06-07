package ex_02_09.ex_01_02;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

record Person(String name, int age, String city) {};

public class Main {
    public static void main(String[] args) {
        String json = """
                {
                    "name": "Claudio",
                    "age": 35,
                    "city": Belém
                }""";

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Person person = gson.fromJson(json, Person.class);
        System.out.println(person);
    }
}
