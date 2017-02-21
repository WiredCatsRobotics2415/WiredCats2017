package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.StatusFrameRate;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	static double kU0 = 2,
				  kP0 = 0.0032*30*kU0,
				  kI0 = 0.0*1.2*kU0,
				  kD0 = 5.0*kP0*kU0,
				  kF0 = .023;
	
	static double kU1 = 2,
				  kP1 = 0.0032*30*kU1,
				  kI1 = 0.0*1.2*kU1,
				  kD1 = 100.0*kP1*kU1,
				  kF1 = .023;
	
	private CANTalon shooterTalon;
	public byte rampProfile = 0, maintainProfile = 1;
	public double shooterSpeed = 2900;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public ShooterSubsystem(){
    	shooterTalon = new CANTalon(RobotMap.SHOOTER_TALON);
    	shooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	shooterTalon.reverseSensor(true);
    	shooterTalon.changeControlMode(TalonControlMode.Speed);
    	shooterTalon.setStatusFrameRateMs(StatusFrameRate.Feedback, 1);
		shooterTalon.configPeakOutputVoltage(12, 0);
    }
    
    public void setSpeed(double speed){
    	shooterTalon.set(speed);
    }
    
    public double getSpeed(){
    	return shooterTalon.getSpeed();
    }
    
    public boolean rampedUp(){
    	return shooterTalon.getSpeed() >= shooterSpeed*1;
    }
    
    public void changeProfile(byte profile){
    	if(profile == rampProfile){
    		shooterTalon.setPID(kP0, kI0, kD0);
    		shooterTalon.setF(kF0);
    	} else if(profile == maintainProfile){
    		shooterTalon.setPID(kP1, kI1, kD1);
    		shooterTalon.setF(kF1);
    	}
    }
    
    
}

