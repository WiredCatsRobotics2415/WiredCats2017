package org.usfirst.frc.team2415.robot.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.command.Command;

public class DataCommand extends Command {

	protected BufferedWriter writer;
	
	public DataCommand() {
		// TODO Auto-generated constructor stub
	}

	public DataCommand(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public DataCommand(double timeout) {
		super(timeout);
		// TODO Auto-generated constructor stub
	}

	public DataCommand(String name, double timeout) {
		super(name, timeout);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * So each data command class has it's own buffered writer to write data to.
	 * this method creates the file on the USB that's plugged in (either V or U drive)
	 * @param fileName the name of the file
	 */
	protected void makeWriter(String fileName){
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
	
	/**
	 * call this at the tail end of the execute function to write data values
	 * to the buffered reader
	 * @param data an array of data values (booleans, doubles, ints, whatever)
	 * 		  that you want to send
	 */
	protected void addThings(Object[] data){
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
	
	/**
	 * flushes and closes the buffered writer
	 */
	protected void flushAndClose(){
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
