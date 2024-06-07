package ex_02_09.ex_03;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        String json = """
                {
                    "title": "Java - How to Program",
                    "author": "P. Deitel",
                    "publisher": {
                                "name": "Pearson",
                                "city": "Baltimore",
                                "isAcademic": true   
                    }
                }""";

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Book book = gson.fromJson(json, Book.class);
        System.out.println(book);
    }
}
