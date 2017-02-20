package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FeederSubsystem extends PIDSubsystem {

	static double kP = 0.00003,
				  kI = 0.000010,
				  kD = 0.0001420,
				  kF = 1/6000;
	
//	static double kP = 0.00003,
//			  kI = 0.0000420,
//			  kD = 0.000075,
//			  kF = 1/6000;
	
	
	private CANTalon feederTalon;
	int encoderDirection = -1;
	public double feederSpeed = 4000;
	
    // Initialize your subsystem here
    public FeederSubsystem() {
    
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	super("feeder", kP, kI, kD, kF);
    	
    	feederTalon = new CANTalon (RobotMap.FEEDER_TALON);
    	feederTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return getSpeed();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	feederTalon.pidWrite(output);
    }
    
    public void setTalonSpeed(double speed){
    	feederTalon.set(speed);
    }
    
    public double getSpeed(){
    	return encoderDirection*feederTalon.getSpeed();
    }
    
    public boolean rampedUp(){
    	return getSpeed() >= feederSpeed*.95;
    }
}
