package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon intakeTalon;
	private Solenoid intakeSolenoid1,intakeSolenoid2;
	//make solenoids
	
	public IntakeSubsystem(){
		intakeTalon = new CANTalon(RobotMap.INTAKE_TALON);
		intakeSolenoid1 = new Solenoid(RobotMap.INTAKE_SOLENOID[0]);
		intakeSolenoid2 = new Solenoid(RobotMap.INTAKE_SOLENOID[1]);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSolenoid(boolean state){
    	intakeSolenoid1.set(state);
    	intakeSolenoid2.set(!state);
    }
    
    public void setMotor(double speed){
    	intakeTalon.set(-speed);
    }
    
    public double getCurrent(){
    	return intakeTalon.getOutputCurrent();
    }
    
    public double getMotor(){
    	return intakeTalon.get();
    }
}
