package org.usfirst.frc.team2415.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCommand extends Command {

    public ShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//TODO: make it require the shooter subsystem
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO: stop the flywheel
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO: set the flywheel to max speed
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//TODO: stop the flywheel
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//TODO: stop the flywheel
    }
}
