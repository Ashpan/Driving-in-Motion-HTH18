package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterFU {
    public static void whenAppendStringUsingBufferedWritter_thenOldContentShouldExistToo() 
      throws IOException {
        String str = "World";
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Harsh\\Desktop\\test.txt", true));
        for(int i = 0; i < 101; i++) {
            writer.append(Integer.toString(i)+"\n");
            writer.newLine();
        }
        writer.close();
    }
    public static void main(String[] args) throws IOException {
        whenAppendStringUsingBufferedWritter_thenOldContentShouldExistToo();
    }
}