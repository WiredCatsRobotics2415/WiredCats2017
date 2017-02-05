package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {
	
	long jamTime;

    public IntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO: extend intake
    	Robot.intakeSubsystem.setMotor(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.intakeSubsystem.getCurrent() >= 10){
    		jamTime = System.currentTimeMillis()/1000;
    	}
    	
    	if(System.currentTimeMillis()/1000 - jamTime <= 0.25) {
    		Robot.intakeSubsystem.setMotor(-0.25);
    	} else Robot.intakeSubsystem.setMotor(0.75);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//TODO: retract intake
    	long overTime = System.currentTimeMillis()/1000;
    	while(System.currentTimeMillis()/1000 - overTime <= 0.25){
    		Robot.intakeSubsystem.setMotor(0.75);
    	}
    	Robot.intakeSubsystem.setMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//TODO: retract intake
    	long overTime = System.currentTimeMillis()/1000;
    	while(System.currentTimeMillis()/1000 - overTime <= 0.25){
    		Robot.intakeSubsystem.setMotor(0.75);
    	}
    	Robot.intakeSubsystem.setMotor(0);
    }
}