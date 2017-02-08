package org.usfirst.frc.team2415.robot.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface USBWritable {
	
	static void makeWriter(BufferedWriter writer, String fileName){
		try {
			writer = new BufferedWriter(new FileWriter(new File("/V/" + fileName + ".csv")));
			System.out.println("File Path: " + "/V/" + fileName + ".csv");
		} catch (IOException e) {
			try {
				writer = new BufferedWriter(new FileWriter(new File("/U/" + fileName + ".csv")));
				System.out.println("File Path: " + "/U/" + fileName + ".csv");
			} catch (IOException f) {
				f.printStackTrace();
				System.out.println("Error Finding a Path");
			}
			e.printStackTrace();
		}
	}
	
	static void addThings(Object[] data, BufferedWriter writer){
		if(data.length < 1){
			for (int i = 0; i < data.length - 1; i++){
				try {
		    		System.out.println(writer);
					writer.write(data[i] + ",");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			writer.write(data[data.length] + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	static void flushAndClose(BufferedWriter writer){
		try {
    		System.out.println("I am about to flush the toilet");
			writer.flush();
			writer.close();
			System.out.println("The dookie is down the drain");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
