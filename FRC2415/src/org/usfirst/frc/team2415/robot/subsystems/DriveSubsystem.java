package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.ArcadeDriveCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
	private CANTalon leftTalBack, leftTalFront, rightTalBack, rightTalFront;
	private AHRS ahrs;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArcadeDriveCommand());
    }
    
    public DriveSubsystem() {
    	ahrs = new AHRS(SPI.Port.kMXP);
    	
    	leftTalBack = new CANTalon(RobotMap.LEFT_TALON_BACK);
    	leftTalFront = new CANTalon(RobotMap.LEFT_TALON_FRONT);
    	rightTalBack = new CANTalon(RobotMap.RIGHT_TALON_BACK);
    	rightTalFront = new CANTalon(RobotMap.RIGHT_TALON_FRONT);

    	leftTalFront.changeControlMode(TalonControlMode.Follower);
    	leftTalFront.set(leftTalBack.getDeviceID());
    	rightTalFront.changeControlMode(TalonControlMode.Follower);
    	rightTalFront.set(rightTalBack.getDeviceID());
    	
    	leftTalBack.reverseOutput(true);
    	
    }
    
    public void stopMotors() {
    	leftTalBack.set(0);
    	rightTalBack.set(0);
    }
    
    public void setMotors(double left, double right) {
     	leftTalBack.set(left);
     	rightTalBack.set(right);
    }
    
    public void changeControlMode(TalonControlMode mode){
    	leftTalBack.changeControlMode(mode);
    	rightTalBack.changeControlMode(mode);
    	
    	if(mode == TalonControlMode.Speed){
    		//TODO: see 12.4
    	}
    }
    
    public void setPIDF(CANTalon talon, double kP, double kI, double kD, double kF){
    	talon.setProfile(0);
    	talon.setPID(kP, kI, kD);
    	talon.setF(kF);
    }
    
    public double getPitch(){
    	return ahrs.getPitch();
    }
    
    public double getYaw(){
    	return ahrs.getYaw();
    }
    
    public double getRoll(){
    	return ahrs.getRoll();
    }
    
    public double getAngle(){
    	return ahrs.getAngle();
    }
    
    public void updateStatus() {
    	
    }
   
}

