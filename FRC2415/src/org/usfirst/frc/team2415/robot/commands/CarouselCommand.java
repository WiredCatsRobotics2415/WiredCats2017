package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CarouselCommand extends Command {

	long backTime;
	boolean voltageSpike;
	
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
    	voltageSpike = (Robot.carouselSubsystem.getCurrent() >= 10);
    	if(voltageSpike) {
    		backTime = System.currentTimeMillis()/1000;
    	}
		if (System.currentTimeMillis()/1000 - backTime < 0.25){
			Robot.carouselSubsystem.setCarouselSpeed(-0.25);
		} else {
			Robot.carouselSubsystem.setCarouselSpeed(0.5);
		}
    	
		/* ok it is 2am not sure if this works either but it seems more logical
		 * so even tho current goes back to normal when the talon goes backwards
		 * shouldnt it not reset backTime so that it will complete the 0.25sec
		 * of -0.25 speed? and if voltage never spikes it will always set to 0.5?
		 * maybe im missing something again
		 */
		
		
    	/* KAMI:
    	 * so this won't work. in understand where your logic is going 
    	 * but there are 2 tiny problems. a few hints:
    	 * 1	so when a ball gets stuck in the intake and the intake starts
    	 * 		to go in reverse, the current goes back down to normal levels
    	 * 2	a while loop in this situation isn't really optimal because
    	 * 		it doesn't let you choose between two options like an if
    	 * 		statement does
    	 * 3	you're missing an else -- find out where
    	 */
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
