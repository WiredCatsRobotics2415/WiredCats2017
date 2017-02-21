package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.StreamerPacket;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCommand extends Command {

	
    public ShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooterSubsystem.setSpeed(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	if(Robot.carouselSubsystem.isMoving()) {
//    		Robot.shooterSubsystem.changeProfile(Robot.shooterSubsystem.maintainProfile);
//    	} else {
//    		Robot.shooterSubsystem.changeProfile(Robot.shooterSubsystem.rampProfile);
//    	}
    	
    	Robot.shooterSubsystem.setSpeed(Robot.shooterSubsystem.shooterSpeed);
    	
    	System.out.println("Speed: " + Robot.shooterSubsystem.getSpeed());
    	
    	StreamerPacket data = new StreamerPacket("shooterData");
    	data.addAttribute("ShooterSpeed", Robot.shooterSubsystem.getSpeed());
    	Robot.dataSender.send(data);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterSubsystem.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooterSubsystem.setSpeed(0);
    }
}
