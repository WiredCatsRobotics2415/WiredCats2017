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
public class TurnToCommand extends Command {
	
	double angle, tolerance = 0.5;
	
	PIDController turnController;

    public TurnToCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.driveSubsystem.changeControlMode(TalonControlMode.PercentVbus);
    	
    	PIDSource gyro = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}

			@Override
			public double pidGet() {
				return Robot.driveSubsystem.getYaw();
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		};

		PIDOutput drivetrain = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				Robot.driveSubsystem.setMotors(output, -output);
			}
		};
		
		final double kP = 0.02;
		final double kI = 0.005;
		final double kD = 0.05;
		
		turnController = new PIDController(kP, kI, kD, gyro, drivetrain);
		
		final double MIN_SPEED = 0.5;
		final double MAX_SPEED = 1.0;
		
		if (angle > 0) {
			turnController.setOutputRange(MIN_SPEED, MAX_SPEED);
		}
		else {
			turnController.setOutputRange(-MIN_SPEED, -MAX_SPEED);
		}
		
		turnController.setAbsoluteTolerance(tolerance);
		turnController.setContinuous(true);
		turnController.setInputRange(-180.0f,  180.0f);
    	turnController.setOutputRange(-1.0, 1.0);
		turnController.setSetpoint(angle);
		turnController.enable();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return turnController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	turnController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
