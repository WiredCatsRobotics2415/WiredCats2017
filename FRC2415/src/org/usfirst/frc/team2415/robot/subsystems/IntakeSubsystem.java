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
	private Solenoid intakeSolenoidRetract, intakeSolenoidExtend;
	
	public IntakeSubsystem(){
		intakeTalon = new CANTalon(RobotMap.INTAKE_TALON);
		intakeSolenoidRetract = new Solenoid(RobotMap.PCM_ID, RobotMap.INTAKE_SOLENOID[1]);
		intakeSolenoidExtend = new Solenoid(RobotMap.PCM_ID, RobotMap.INTAKE_SOLENOID[0]);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSolenoid(boolean state){
    	intakeSolenoidRetract.set(!state);
    	intakeSolenoidExtend.set(state);
    }
    
    public void setMotor(double speed){
    	intakeTalon.set(speed);
    }
    
    public double getCurrent(){
    	return intakeTalon.getOutputCurrent();
    }
    
    public double getMotor(){
    	return intakeTalon.get();
    }
}
