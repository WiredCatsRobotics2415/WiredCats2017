package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearManipulatorSubsystem extends Subsystem {

	private Solenoid gearManipSolenoid1,gearManipSolenoid2;
	private Solenoid gearPushSolenoid1,gearPushSolenoid2;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public GearManipulatorSubsystem(){
		gearManipSolenoid1 = new Solenoid(RobotMap.GEAR_MANIP_SOLENOID[0]);
		gearManipSolenoid2 = new Solenoid(RobotMap.GEAR_MANIP_SOLENOID[1]);
		gearPushSolenoid1 = new Solenoid(RobotMap.GEAR_PUSH_SOLENOID[0]);
		gearPushSolenoid2 = new Solenoid(RobotMap.GEAR_PUSH_SOLENOID[1]);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setManipSolenoid(boolean state){
    	gearManipSolenoid1.set(state);
    	gearManipSolenoid2.set(!state);
    }
    
    public void setPushSolenoid(boolean state){
    	gearPushSolenoid1.set(state);
    	gearPushSolenoid2.set(!state);
    }
}

