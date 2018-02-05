package main;

import java.io.*;

public class FileWriterFU {
	public static void write(double a)
			throws IOException {
		a /= 90;
		//screw this comment
//		System.out.println(System.getProperty("user.dir"));
//		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Harsh\\Desktop\\HackHammerNU\\Assets\\Resources\\stream.txt", true));
//		if(!Double.toString(a).equals("NaN")) {
//			writer.append(Double.toString(a));
//			writer.newLine();
//		}
//        writer.close();
		RandomAccessFile f = new RandomAccessFile(new File("C:\\Users\\Harsh\\Desktop\\HackHammerNU\\Assets\\Resources\\stream.txt"), "rw");
		if (!Double.toString(a).equals("NaN")) {
			f.seek(0); // to the beginning
			f.write(Double.toString(a).getBytes());
		}
		f.close();
	}

	public static void writespeed(boolean moving)
			throws IOException {
//        writer.close();
		RandomAccessFile f = new RandomAccessFile(new File("C:\\OurData\\OurCurrent\\Programming\\Driving-in-Motion-HTH18\\Unity Stuff\\HackHammerNU\\Assets\\Resources\\moving.txt"), "rw");
		f.seek(0); // to the beginning
		f.writeBoolean(moving);
		f.close();
	}
}



//    public static void delete() throws IOException{
//    	PrintWriter writer = new PrintWriter("C:\\Users\\Harsh\\Desktop\\HackHammerNU\\Assets\\Resources\\stream.txt");
//    	writer.print("");
//    	writer.close();
//    }

