
package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.StreamerPacket;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CarouselCommand extends Command {

	long backTime, startTime, jamTime;
	boolean voltageSpike, checked = false;
	double sign = 0;
	double currentCap = 10.69, reverseTime = 0.05;

	public CarouselCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		// TODO: make it require the carousel subsystem
		requires(Robot.carouselSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// TODO: make the carousel stop and 3 second delay

    	startTime = System.currentTimeMillis()/1000;

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(!checked){
    		if(!Robot.feederSubsystem.rampedUp()) {
    			startTime = System.currentTimeMillis()/1000;
    			return;
    		}
    		if(startTime < 0.25){
    			checked = true;
    		}
    	}
		
		/*
		 * TODO: so the carousel should always be moving forward at half speed
		 * unless it senses that the voltage spikes to some arbitrary number
		 * that we find out later but lets just call it 10 for now then it needs
		 * to go in reverse for a quarter of a second and then continue to go
		 * forward
		 */
		
		if(Robot.intakeSubsystem.getCurrent() >= currentCap && System.currentTimeMillis()/1000 - startTime >= 0.25 && Math.signum(Robot.intakeSubsystem.getMotor()) == sign){
    		jamTime = System.currentTimeMillis()/1000;
    	}
    	
    	if(System.currentTimeMillis()/1000 - jamTime <= reverseTime) {
    		Robot.carouselSubsystem.setCarouselSpeed(-0.25);
    	} else Robot.carouselSubsystem.setCarouselSpeed(.25);
    	
    	sign = Robot.intakeSubsystem.getMotor()/(Math.abs(Robot.intakeSubsystem.getMotor()));

    	Robot.carouselSubsystem.isMoving = true;
		
//		StreamerPacket data = new StreamerPacket("carouselData");
//    	data.addAttribute("carouselCurrent", Robot.carouselSubsystem.getCurrent());
//    	Robot.dataSender.send(data);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.carouselSubsystem.setCarouselSpeed(0);
		checked = false;
    	Robot.carouselSubsystem.isMoving = false;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		// TODO: make the carousel stop
		Robot.carouselSubsystem.setCarouselSpeed(0);
		checked = false;
    	Robot.carouselSubsystem.isMoving = false;
	}
}
