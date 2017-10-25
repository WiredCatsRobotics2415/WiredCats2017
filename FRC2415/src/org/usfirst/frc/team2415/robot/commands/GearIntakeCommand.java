package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearIntakeCommand extends Command {

	double speed;
	boolean hasGear = false;
	long startTime;
	
    public GearIntakeCommand(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.groundGearSubsystem);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.groundGearSubsystem.dropIntake();
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
			Robot.groundGearSubsystem.setMotor(-0.75);

		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(speed == 0) return true;
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
