package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDFeederSubsystem extends PIDSubsystem {

    // Initialize your subsystem here
    private CANTalon feederTalon;

	static double kP = 0.1,
				  kI = 0,
				  kD = 0,
				  kF = 0;
    
    public PIDFeederSubsystem() {
    	super("feeder", kP, kI, kD, kF);

    	feederTalon = new CANTalon(RobotMap.FEEDER_TALON);
    	feederTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return feederTalon.getSpeed();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	feederTalon.set(output);
    }

	public void setMotor(double speed) {
		feederTalon.set(speed);
		
	}
}
