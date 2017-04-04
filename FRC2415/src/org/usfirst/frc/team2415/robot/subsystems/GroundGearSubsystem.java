package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.GroundGearStateCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GroundGearSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public final byte GROUND = 0,
				      CARRY = 1,
				      LIMP = 2;
	
	private DoubleSolenoid gearManip;
	private CANTalon intakeTalon;
	private DigitalInput button;
	public byte intakeState;

    public GroundGearSubsystem(){
    	gearManip = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.GEAR_MANIP_SOLENOID[0], RobotMap.GEAR_MANIP_SOLENOID[1]);
    	intakeTalon = new CANTalon(RobotMap.GM_INTAKE);
    	button = new DigitalInput(1);
    	
    	limpDick();
    	intakeState = LIMP;
    }
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//        setDefaultCommand(new GroundGearStateCommand());
    }
    
	public void dropIntake(){
		gearManip.set(Value.kForward);
		intakeState = GROUND;
	}
	
	public void raiseIntake(){
		gearManip.set(Value.kReverse);
		intakeState = CARRY;
	}
	
	public void limpDick(){
		gearManip.set(Value.kOff);
		intakeState = LIMP;
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
}

