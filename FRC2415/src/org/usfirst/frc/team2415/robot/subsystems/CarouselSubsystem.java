package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.CarouselCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class CarouselSubsystem extends PIDSubsystem {

	private CANTalon carouselTal;
	static double kP = 0, kI = 0, kD = 0, kF = 0;
	
    // Initialize your subsystem here
    public CarouselSubsystem() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("Carousel", kP, kI, kD, kF);
    	setAbsoluteTolerance(0.5); // arbitrary numbers...
    	setSetpoint(10);
		getPIDController().setContinuous(false);
		enable();
    	
    	carouselTal = new CANTalon(RobotMap.CAROUSEL_TALON);

    }

    public void initDefaultCommand() {
    	setDefaultCommand(new CarouselCommand());
    	
    	/* KAMI:
    	 * no need to set it as the default 
    	 * command since you're running it on a button
    	 */
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        return carouselTal.getOutputCurrent();
        
        //is this what im supposed to use for the PID input??
        /* 
         * KAMI: no--you're supposed to use the speed as input
         * 		 but it doesn't matter because the carousel
         * 		 doesn't have an encoder on it sorry :D
         * 		 just make a regular subsystem and
         * 		 run the talon on percentvbus and set the
         * 		 forward speed to 0.69 or something
         */
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
    	carouselTal.pidWrite(output);
    }
    
    public void setCarouselSpeed(double speed){
    	carouselTal.set(speed);
    }
    
    public double getCurrent(){
    	return carouselTal.getOutputCurrent();
    }
    
}


