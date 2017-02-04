package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CarouselCommand extends Command {

    public CarouselCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//TODO: make it require the carousel subsystem
    	requires(Robot.carouselSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO: make the carousel stop and 3 second delay
    	long initTime = System.currentTimeMillis()/1000;
    	while(initTime + 3 < System.currentTimeMillis()/1000){
    		Robot.carouselSubsystem.setCarouselSpeed(0);
    	}
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
    	if(Robot.carouselSubsystem.getCurrent() >= 10){
    		long backTime = System.currentTimeMillis()/1000;
    		while (System.currentTimeMillis()/1000 - backTime < 0.25){
    			Robot.carouselSubsystem.setCarouselSpeed(-0.25);
    		}
    	}
    	Robot.carouselSubsystem.setCarouselSpeed(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.carouselSubsystem.setCarouselSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//TODO: make the carousel stop
    	Robot.carouselSubsystem.setCarouselSpeed(0);
    }
}
