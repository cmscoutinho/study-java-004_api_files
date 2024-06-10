package ex_04_09.ex_01.main;

import ex_04_09.ex_01.model.SimpleMessageWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            SimpleMessageWriter writer = new SimpleMessageWriter("file.txt");
            writer.writeMsg();
            writer.close();
            System.out.println("The file was saved!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
