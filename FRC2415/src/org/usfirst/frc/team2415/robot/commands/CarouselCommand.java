package org.usfirst.frc.team2415.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CarouselCommand extends Command {

    public CarouselCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//TODO: make it require the carousel subsystem
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO: make the carousel stop and 3 second delay
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/* TODO:
    	 * so the carousel should always be moving forward at half
    	 * speed unless it senses that the voltage spikes to some
    	 * arbitrary number that we find out later but lets just call it 10
    	 * for now then it needs to go in reverse for a quarter of a second
    	 * and then continue to go forward
    	 */
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//TODO: make the carousel stop
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//TODO: make the carousel stop
    }
}
