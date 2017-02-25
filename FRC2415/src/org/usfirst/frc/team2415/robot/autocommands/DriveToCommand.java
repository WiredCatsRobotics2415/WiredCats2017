package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToCommand extends Command {

	private double distance;
	private double tolerance = 0.5;

	private PIDController leftMotorController;
	private PIDController rightMotorController;

	public DriveToCommand(double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveSubsystem);
		this.distance = distance;
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		Robot.driveSubsystem.zeroEncoders();
		Robot.driveSubsystem.changeControlMode(TalonControlMode.PercentVbus);

		PIDSource leftMotorSource = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}

			@Override
			public double pidGet() {
				return Robot.driveSubsystem.getDistance()[0];
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		};

		PIDSource rightMotorSource = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}

			@Override
			public double pidGet() {
				return Robot.driveSubsystem.getDistance()[1];
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		};

		PIDOutput leftMotorOutput = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				Robot.driveSubsystem.setLeft(output);
			}
		};

		PIDOutput rightMotorOutput = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				Robot.driveSubsystem.setRight(output);
			}
		};

		final double Kp = 0.3;
		final double Ki = 0.0;
		final double Kd = 0.001;
		
		leftMotorController = new PIDController(Kp, Ki, Kd, leftMotorSource, leftMotorOutput);
		rightMotorController = new PIDController(Kp, Ki, Kd, rightMotorSource, rightMotorOutput);
		
		leftMotorController.setAbsoluteTolerance(tolerance);
		rightMotorController.setAbsoluteTolerance(tolerance);
		
		final double MIN_SPEED = 0.1;
		final double MAX_SPEED = 0.5;
		
		if (distance > 0) {
			leftMotorController.setOutputRange(MIN_SPEED, MAX_SPEED);
			rightMotorController.setOutputRange(MIN_SPEED, MAX_SPEED);
		}
		else {
			leftMotorController.setOutputRange(-MIN_SPEED, -MAX_SPEED);
			rightMotorController.setOutputRange(-MIN_SPEED, -MAX_SPEED);
		}
		
		leftMotorController.setSetpoint(distance);
		rightMotorController.setSetpoint(distance);
		
		leftMotorController.enable();
		rightMotorController.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return leftMotorController.onTarget() && rightMotorController.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
    	leftMotorController.disable();
    	rightMotorController.disable();
    	Robot.driveSubsystem.stopMotors();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
    	leftMotorController.disable();
    	rightMotorController.disable();
    	Robot.driveSubsystem.stopMotors();
	}
}
