package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GroundGearSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	/*idk if this is a solenoid or a double solenoid or how this
	 *subsytem is supposed to work but hey you guys can figure
	 *it out :) also yolo trying out double solenoids if it doesn't
	 *work just change them to two solenoids per double solenoid
	 */
	
	public byte GROUND = 0,
				CARRY = 1;
	
	private DoubleSolenoid gearManip;
	private CANTalon intakeTalon;

    public GroundGearSubsystem(){
    	//someone change these ports
    	gearManip = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.GEAR_MANIP_SOLENOID[0], RobotMap.GEAR_MANIP_SOLENOID[1]);
    	
    	intakeTalon = new CANTalon(RobotMap.GM_INTAKE);
    }
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void dropIntake(){
		gearManip.set(Value.kForward);
	}
	
	public void raiseIntake(){
		gearManip.set(Value.kReverse);
	}
	
	public void limpDick(){
		gearManip.set(Value.kOff);
	}
	
	public void setMotor(double speed){
		intakeTalon.set(speed);
	}
	
	public double getCurrent(){
		return intakeTalon.getOutputCurrent();
	}
}

