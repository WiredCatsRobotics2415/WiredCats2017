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
	
	static double kU = 2;
	static double kP = 0.0032*30*kU,
				  kI = 0.0*1.2*kU,
				  kD = 5.0*kP*kU,
				  kF = .023;
	
	private CANTalon shooterTalon;
	public int rampProfile = 0, maintainProfile = 1;
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
//		shooterTalon.configPeakOutputVoltage(12, 0);
		shooterTalon.setPID(kP, kI, kD);
		shooterTalon.setF(kF);
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
    
    public void changeProfile(int profile){
    	shooterTalon.setProfile(profile);
    }
    
    
}

