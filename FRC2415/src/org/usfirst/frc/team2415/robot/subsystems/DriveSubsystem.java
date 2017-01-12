package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.ArcadeDriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
	public CANTalon LeftTalBack, LeftTalFront, RightTalBack, RightTalFront;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArcadeDriveCommand());
    }
    
    public DriveSubsystem() {
    	LeftTalBack = new CANTalon(RobotMap.LEFT_TALON_BACK);
    	LeftTalFront = new CANTalon(RobotMap.LEFT_TALON_FRONT);
    	RightTalBack = new CANTalon(RobotMap.RIGHT_TALON_BACK);
    	RightTalFront = new CANTalon(RobotMap.RIGHT_TALON_FRONT);
    	
    }
    
    public void stopMotors() {
    	LeftTalBack.set(0);
    	LeftTalFront.set(0);
    	RightTalBack.set(0);
    	RightTalFront.set(0);
    }
    
    public void setMotors(double left, double right) {
     	LeftTalBack.set(left);
     	LeftTalFront.set(left);
     	RightTalBack.set(-right);//INVERTING HERE, CHECK BACK
     	RightTalFront.set(-right);
    }
   
}

