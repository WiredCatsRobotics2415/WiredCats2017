package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.GroundGearCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GroundGearSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public final byte GROUND = 0,
				      CARRY = 1;
	
	private Solenoid gearManip;
	private CANTalon intakeTalon;
	private DigitalInput button;
	public byte intakeState;
	public static DigitalInput leftIR;

    public GroundGearSubsystem(){
    	gearManip = new Solenoid(RobotMap.PCM_ID, RobotMap.GEAR_MANIP_SOLENOID);
    	intakeTalon = new CANTalon(RobotMap.GM_INTAKE);
//    	rightIR = new DigitalInput(1);
    	leftIR = new DigitalInput(2);
    	
    }
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//        setDefaultCommand(new GroundGearCommand(Robot.groundGearSubsystem.CARRY,0));
    }
    
	public void dropIntake(){
		gearManip.set(true);
	}
	
	public void raiseIntake(){
		gearManip.set(false);
	}
	
//	public void limpDick(){
//		gearManip.set(false);
//		intakeState = LIMP;
//	}
	
	public boolean getState(){
		return gearManip.get();
	}
	
	public void setMotor(double speed){
		intakeTalon.set(speed);
	}
	
	public double getCurrent(){
		return intakeTalon.getOutputCurrent();
	}
	
	public boolean getButton(){
		return !button.get();
	}
	
	public boolean getIR(){
    	return leftIR.get();
    }
}

