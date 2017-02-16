package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearManipulatorSubsystem extends Subsystem {

	private Solenoid gearManipSolenoid;
	private Solenoid gearPushSolenoid;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public GearManipulatorSubsystem(){
		gearManipSolenoid = new Solenoid(RobotMap.GEAR_MANIP_SOLENOID);
		gearPushSolenoid = new Solenoid(RobotMap.GEAR_PUSH_SOLENOID);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setManipSolenoid(boolean state){
    	gearManipSolenoid.set(state);
    }
    
    public void setPushSolenoid(boolean state){
    	gearPushSolenoid.set(state);
    }
}

