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
				  kP0 = 0.0032 * 30 * kU0, 
				  kI0 = 0.0 * 1.2 * kU0, 
				  kD0 = 5.0 * kP0 * kU0, 
				  kF0 = .023;

	static double kU1 = 1, 
				  kP1 = 0.01 * 40 * kU1, 
				  kI1 = 0.0000 * kU1, 
				  kD1 = 1.0 * kP1 * kU1, 
				  kF1 = .023;

	private CANTalon shooterTalon;
	public byte rampProfile = 0, maintainProfile = 1;
	public double shooterSpeed = 3000;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public ShooterSubsystem() {
		shooterTalon = new CANTalon(RobotMap.SHOOTER_TALON, 1);
		shooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shooterTalon.reverseSensor(true);
		shooterTalon.changeControlMode(TalonControlMode.Speed);
		shooterTalon.configPeakOutputVoltage(12, 0);

	}

	public void setSpeed(double speed) {
		shooterTalon.set(speed);
	}

	public double getSpeed() {
		return shooterTalon.getSpeed();
	}

	public boolean rampedUp() {
		return shooterTalon.getSpeed() >= shooterSpeed * 1;
	}

	public void changeProfile(byte profile) {
		if (profile == rampProfile) {
			shooterTalon.setProfile(rampProfile);
		} else if (profile == maintainProfile) {
			shooterTalon.setProfile(maintainProfile);
		}
	}

	public double getSetpoint() {
		return shooterTalon.getSetpoint();
	}

	public long getIError() {
		return shooterTalon.GetIaccum();
	}

}
