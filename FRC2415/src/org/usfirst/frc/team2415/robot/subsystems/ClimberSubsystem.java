package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	CANTalon climberTalon;
	
	public ClimberSubsystem(){
		climberTalon = new CANTalon(RobotMap.CLIMBER_TALON);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setMotor(double speed){
    	climberTalon.set(speed);
    }
    
    public double getVoltage(){
    	return climberTalon.getOutputVoltage();
    }
    
    public double getMotor(){
    	return climberTalon.get();
    }
}

