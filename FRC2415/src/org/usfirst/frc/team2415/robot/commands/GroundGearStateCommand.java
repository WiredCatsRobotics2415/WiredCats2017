package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GroundGearStateCommand extends Command {

	
	
    public GroundGearStateCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.groundGearSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (Robot.groundGearSubsystem.intakeState) {
    		case 0: Robot.groundGearSubsystem.dropIntake();
    				break;
    		case 1: Robot.groundGearSubsystem.raiseIntake();
    				break;
    		case 2: Robot.groundGearSubsystem.limpDick();
    				break;
    	
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
