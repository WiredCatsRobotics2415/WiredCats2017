package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearManipulatorSubsystem extends Subsystem {

	private Solenoid gearManipSolenoidExtend,gearManipSolenoidRetract;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public GearManipulatorSubsystem(){
		gearManipSolenoidExtend = new Solenoid(RobotMap.PCM_ID, RobotMap.GEAR_MANIP_SOLENOID[0]);
		gearManipSolenoidRetract = new Solenoid(RobotMap.PCM_ID, RobotMap.GEAR_MANIP_SOLENOID[1]);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setManipSolenoid(boolean state){
    	gearManipSolenoidExtend.set(state);
    	gearManipSolenoidRetract.set(!state);
    }
}

