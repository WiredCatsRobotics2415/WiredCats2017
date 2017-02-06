package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.commands.ShooterCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	private CANTalon shooterTalon;
    
	 shooterTalon = new CANTalon (RobotMap.Shooter_Talon);
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShooterCommand());
    }
    
  
    public void setTalonSpeed(){
    	shooterTalon.set(0.0);
    }
}

