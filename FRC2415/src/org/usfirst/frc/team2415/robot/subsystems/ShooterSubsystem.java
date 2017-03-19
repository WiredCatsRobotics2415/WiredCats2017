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
				  kP0 = 0,//0.162
				  kI0 = 0, 
				  kD0 = 0, //1.92
				  kF0 = 1;

	static double kU1 = 1, 
				  kP1 = 0.4, 
				  kI1 = 0, 
				  kD1 = 0.4, 
				  kF1 = 0.000154345;

	private CANTalon shooterTalon;
	public byte rampProfile = 0, maintainProfile = 1;
	public double shooterSpeed = 3000;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public ShooterSubsystem() {
		shooterTalon = new CANTalon(RobotMap.SHOOTER_TALON, 1);
		shooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		shooterTalon.reverseSensor(true);
		shooterTalon.changeControlMode(TalonControlMode.Speed);
		shooterTalon.configPeakOutputVoltage(12, 0);
		
		shooterTalon.setProfile(rampProfile);
		shooterTalon.setPID(kP0, kI0, kD0);
		shooterTalon.setF(kF0);

		shooterTalon.setProfile(maintainProfile);
		shooterTalon.setPID(kP1, kI1, kD1);
		shooterTalon.setF(kF1);

	}

	public void setSpeed(double speed) {
		shooterTalon.set(speed);
	}

	public double getSpeed() {
		return shooterTalon.getSpeed();
	}

	public boolean rampedUp() {
		return Math.abs(shooterTalon.getSpeed()) >= shooterSpeed*.98;
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
