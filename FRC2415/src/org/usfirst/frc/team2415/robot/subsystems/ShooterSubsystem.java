package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class ShooterSubsystem extends PIDSubsystem {

	static double kP = 0.1,
				  kI = 0,
				  kD = 0,
				  kF = 0;
	private CANTalon shooterTalon;
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
        return shooterTalon.getSpeed();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	shooterTalon.pidWrite(output);
    }
    
    public void setTalonSpeed(double speed){
    	shooterTalon.set(speed);
    }
    
}
