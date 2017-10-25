package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GroundGearCommand extends Command {

	byte state;
	double speed;
	double currentCap = 11, timeout = -1, timeoutStart;
	long gearTime, startTime;
	boolean hasGear = false;
	
	public GroundGearCommand(byte state) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.groundGearSubsystem);
		this.state = state;
		this.speed = 0;
	}
	
	public GroundGearCommand(byte state, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.groundGearSubsystem);
		this.state = state;
		this.speed = speed;
	}
	
	public GroundGearCommand(byte state, double speed, double timeout) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.groundGearSubsystem);
		this.state = state;
		this.speed = speed;
		this.timeout = timeout;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		timeoutStart = System.currentTimeMillis();
		
		if (state == Robot.groundGearSubsystem.GROUND) {
			Robot.groundGearSubsystem.dropIntake();

		} else {
			Robot.groundGearSubsystem.raiseIntake();
		}
		
		Robot.groundGearSubsystem.setMotor(speed);
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		
		if (Robot.groundGearSubsystem.getIR()) {
			hasGear = true;
		} else {
			hasGear = false;
		}
		
		if (!hasGear) {
			startTime = System.currentTimeMillis() / 1000;
		}
		
		if (System.currentTimeMillis() / 1000 - startTime >= 0.05
				&& Robot.groundGearSubsystem.getIR()) {
			Robot.groundGearSubsystem.raiseIntake();
			Robot.groundGearSubsystem.setMotor(-0.65);

		}
		
			

		
//		if (Robot.groundGearSubsystem.getCurrent() > currentCap) {
//			hasGear = true;
//		} else {
//			hasGear = false;
//		}
//		if (!hasGear) {
//			startTime = System.currentTimeMillis() / 1000;
//		}
//		if (System.currentTimeMillis() / 1000 - startTime >= 0.75
//				&& Robot.groundGearSubsystem.getCurrent() > currentCap) {
//			Robot.groundGearSubsystem.raiseIntake();
//			if (System.currentTimeMillis() / 1000 - startTime <= 1) {
//				Robot.groundGearSubsystem.setMotor(-1);
//			} else {
//				Robot.groundGearSubsystem.setMotor(-0.25);
//			}
//		}
//		
//		System.out.println("Current: " + Robot.groundGearSubsystem.getCurrent());

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(speed == 0) return true;
		if(timeout > 0) return System.currentTimeMillis()/1000 - timeoutStart/1000 >= timeout;
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.groundGearSubsystem.setMotor(0);
		hasGear = false;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.groundGearSubsystem.setMotor(0);
		hasGear = false;
	}
}
