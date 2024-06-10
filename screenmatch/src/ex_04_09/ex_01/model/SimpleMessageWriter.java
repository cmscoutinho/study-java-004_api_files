package ex_04_09.ex_01.model;

import java.io.FileWriter;
import java.io.IOException;

public class SimpleMessageWriter extends FileWriter {

    public SimpleMessageWriter(String fileName) throws IOException {
        super("src/ex_04_09/ex_01/res/" + fileName);
    }

    public void writeMsg() throws IOException {
        this.write("Content to be written to the file");
    }
}
