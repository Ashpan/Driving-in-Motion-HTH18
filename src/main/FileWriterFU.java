package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriterFU {
    public static void write(double a) 
      throws IOException {
    	a /= 90;
		System.out.println(System.getProperty("user.dir"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Harsh\\Desktop\\race_Data\\Resources\\stream.txt", true));
		if(!Double.toString(a).equals("NaN")) {
			writer.append(Double.toString(a));
			writer.newLine();
		}
        writer.close();
    }
    public static void delete() throws IOException{
    	PrintWriter writer = new PrintWriter("C:\\Users\\Harsh\\Desktop\\race_Data\\Resources\\stream.txt");
    	writer.print("");
    	writer.close();
    }

}