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
	
	CANTalon climberTalon1;
	CANTalon climberTalon2;
	
	public ClimberSubsystem(){
		climberTalon1 = new CANTalon(RobotMap.CLIMBER_TALON);
		climberTalon2 = new CANTalon(RobotMap.CLIMBER_TALON2);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setMotor(double speed){
    	climberTalon1.set(speed);
    	climberTalon2.set(-speed);
    }
    
    public double getVoltage(){
    	return climberTalon1.getOutputVoltage();
    }
    
    public double getMotor(){
    	return climberTalon1.get();
    }
}

