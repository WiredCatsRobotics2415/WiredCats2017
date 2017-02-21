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
public class FeederSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	static double kU = 1, 
			  kP = kU*0.00003*.20*3,
			  kI = kU*0.000010*25,
			  kD = kU*0.0001420/25,
			  kF = 1/6000;
	
	private CANTalon feederTalon;
	public int rampProfile = 0, maintainProfile = 1;
	public double feederSpeed = 5000;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public FeederSubsystem(){
    	feederTalon = new CANTalon(RobotMap.FEEDER_TALON);
    	feederTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	feederTalon.reverseSensor(true);
    	feederTalon.changeControlMode(TalonControlMode.Speed);
    	feederTalon.setStatusFrameRateMs(StatusFrameRate.Feedback, 1);
    	feederTalon.setPID(kP, kI, kD);
    	feederTalon.setF(kF);
    }
    
    public void setSpeed(double speed){
    	feederTalon.set(speed);
    }
    
    public double getSpeed(){
    	return feederTalon.getSpeed();
    }
    
    public boolean rampedUp(){
    	return feederTalon.getSpeed() >= feederSpeed*0.98;
    }
    
    public void changeProfile(int profile){
    	feederTalon.setProfile(profile);
    }
    
    
}

