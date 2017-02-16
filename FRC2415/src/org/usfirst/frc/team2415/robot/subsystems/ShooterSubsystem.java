package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSubsystem extends PIDSubsystem {

	static double kP = 0.0015,
				  kI = 0.0000175,
				  kD = 0.001160,
				  kF = 1/6000;
	
	
	private CANTalon shooterTalon;
	int encoderDirection = -1;
	
    // Initialize your subsystem here
    public ShooterSubsystem() {
    
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	super("shooter", kP, kI, kD, kF);
    	
    	shooterTalon = new CANTalon (RobotMap.SHOOTER_TALON);
    	shooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	
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
    	shooterTalon.pidWrite(output);
    }
    
    public void setTalonSpeed(double speed){
    	shooterTalon.set(speed);
    }
    
    public double getSpeed(){
    	return encoderDirection*shooterTalon.getSpeed();
    }
    
    public boolean rampedUp(){
    	return getSpeed() >= 2500;
    }
}
