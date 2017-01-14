package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.ArcadeDriveCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

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

    	LeftTalFront.changeControlMode(TalonControlMode.Follower);
    	LeftTalFront.set(LeftTalBack.getDeviceID());
    	RightTalFront.changeControlMode(TalonControlMode.Follower);
    	RightTalFront.set(RightTalBack.getDeviceID());
    	
    	LeftTalBack.reverseOutput(true);
    	LeftTalFront.reverseOutput(true);
    	
    }
    
    public void stopMotors() {
    	LeftTalBack.set(0);
    	RightTalBack.set(0);
    }
    
    public void setMotors(double left, double right) {
     	LeftTalBack.set(left);
     	RightTalBack.set(right);
    }
    
    public void updateStatus() {
    	
    }
   
}

