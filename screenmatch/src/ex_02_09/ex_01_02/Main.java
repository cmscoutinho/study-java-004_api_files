package ex_02_09.ex_01_02;

import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;

public class Main {
    public static void main(String[] args) {
        String json = """
                {
                    "nome": "Claudio",
                    "idade": 35,
                    "cidade": "Belém"
                }""";

        Gson gson = new Gson();
        Pessoa pessoa = gson.fromJson(json, Pessoa.class);
        System.out.println(pessoa);
    }
}
