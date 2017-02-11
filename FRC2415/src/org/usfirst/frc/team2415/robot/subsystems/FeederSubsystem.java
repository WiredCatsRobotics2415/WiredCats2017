package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.FeederCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FeederSubsystem extends Subsystem {

    private CANTalon feederTalon;
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	
	
	
    public FeederSubsystem() {
    	feederTalon = new CANTalon(RobotMap.FEEDER_TALON);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());

    }
    
    public void setMotor(double speed){
    	feederTalon.set(speed);
    }
}
