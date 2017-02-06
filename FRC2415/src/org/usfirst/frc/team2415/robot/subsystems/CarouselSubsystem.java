package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.CarouselCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CarouselSubsystem extends Subsystem {

	private CANTalon carouselTal;
	
    // Initialize your subsystem here
    public CarouselSubsystem() {
//     	carouselTal = new CANTalon(RobotMap.CAROUSEL_TALON);

    }

    public void initDefaultCommand() {
    	setDefaultCommand(new CarouselCommand());
    	
    	/* KAMI:
    	 * no need to set it as the default 
    	 * command since you're running it on a button
    	 */
    	
    	//ok but i can cant i :3
	    
	// no.
    }

    public void setCarouselSpeed(double speed){
    	carouselTal.set(speed);
    }
    
    public double getCurrent(){
    	return carouselTal.getOutputCurrent();
    }
    
}


