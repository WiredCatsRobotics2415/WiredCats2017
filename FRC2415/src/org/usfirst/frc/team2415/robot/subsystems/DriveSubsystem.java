package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.ArcadeDriveCommand;
import org.usfirst.frc.team2415.robot.utilities.PixyCam;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import cheesy.DriveSignal;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	private TalonSRX leftTalBack, leftTalFront, rightTalBack, rightTalFront;
	public AHRS ahrs;
	private PixyCam pixy;

	public boolean isMoving;

	public final double WHEEL_CIRCUMFERENCE = .270833333 * Math.PI; // 3.25 inch
	final double GEAR_RATIO = 1 / 4.909090909; // Reduction from encoder shaft and output shaft
	final double PULSES_PER_REVOLUTION = 4096.0; // Number of encoder counts per revolution

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ArcadeDriveCommand());
	}

	public DriveSubsystem() {

		try {
			/* Communicate w/navX-MXP via the MXP SPI Bus. */
			/* Alternatively: I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB */
			/*
			 * See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for
			 * details.
			 */
			ahrs = new AHRS(SerialPort.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}

		leftTalBack = new TalonSRX(RobotMap.LEFT_TALON_BACK);
		leftTalFront = new TalonSRX(RobotMap.LEFT_TALON_FRONT);
		rightTalBack = new TalonSRX(RobotMap.RIGHT_TALON_BACK);
		rightTalFront = new TalonSRX(RobotMap.RIGHT_TALON_FRONT);

		leftTalBack.set(ControlMode.Follower, leftTalFront.getDeviceID());
		rightTalBack.set(ControlMode.Follower, rightTalFront.getDeviceID());

		leftTalFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rightTalFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

		leftTalFront.setNeutralMode(NeutralMode.Brake);
		rightTalFront.setNeutralMode(NeutralMode.Brake);

	}

	public void stopMotors() {
		leftTalFront.set(ControlMode.PercentOutput, 0);
		rightTalFront.set(ControlMode.PercentOutput, 0);
	}

	public void setMotors(double left, double right) {
		leftTalFront.set(ControlMode.PercentOutput, -left);
		rightTalFront.set(ControlMode.PercentOutput, right);
	}

	public void setMotors(DriveSignal signal) {
		leftTalFront.set(ControlMode.PercentOutput, -signal.getLeft());
		rightTalFront.set(ControlMode.PercentOutput, signal.getRight());
	}

	public void setLeft(double speed) {
		leftTalFront.set(ControlMode.PercentOutput, -speed);
	}

	public void setRight(double speed) {
		rightTalFront.set(ControlMode.PercentOutput, speed);
	}

	public void setBreakMode(NeutralMode mode) {
		leftTalFront.setNeutralMode(mode);
		rightTalFront.setNeutralMode(mode);
		leftTalBack.setNeutralMode(mode);
		rightTalBack.setNeutralMode(mode);
	}

	public double getPitch() {
		return ahrs.getPitch();
	}

	public double getYaw() {
		return ahrs.getYaw();
	}

	public void zeroYaw() {
		ahrs.zeroYaw();
	}

	public double getRoll() {
		return ahrs.getRoll();
	}

	public double getAngle() {
		return ahrs.getAngle();
	}

	public double fPS2RPM(double fps) {
		return (fps * 60) / (WHEEL_CIRCUMFERENCE);
	}


}
