package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon intakeTalon;
	//make solenoids
	
	public IntakeSubsystem(){
		intakeTalon = new CANTalon(RobotMap.INTAKE_TALON);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setMotor(double speed){
    	intakeTalon.set(-speed);
    }
    
    public double getCurrent(){
    	return intakeTalon.getOutputCurrent();
    }
}
