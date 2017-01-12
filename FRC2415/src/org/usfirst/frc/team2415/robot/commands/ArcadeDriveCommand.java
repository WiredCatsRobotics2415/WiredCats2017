package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDriveCommand extends Command {
	
	private double INTERPOLATION_FACTOR = 0.75;
	private double DEADBAND = 0.05;
	private double STRAIGHT_RESTRICTER = 0.8; 
	private double TURN_SPEED_BOOST = 1.2;

    public ArcadeDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.stopMotors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double leftY;
    	double rightX;
    	
    	if (Robot.singlePlayerMode){
    		leftY = Robot.operator.Y();
    		rightX = Robot.operator.X();
    	}	else {
    		leftY = Robot.gamepad.leftY();
    		rightX = Robot.gamepad.rightX();
    	}
    	
    	if (Math.abs(leftY)< DEADBAND) leftY = 0;
    	if (Math.abs(rightX)< DEADBAND) rightX = 0;
    	
    	leftY = INTERPOLATION_FACTOR*Math.pow(leftY, 3) + (1-INTERPOLATION_FACTOR)*leftY;
    	rightX = INTERPOLATION_FACTOR*Math.pow(rightX, 3) + (1-INTERPOLATION_FACTOR)*rightX;
    	
    	double rightMotors = STRAIGHT_RESTRICTER*leftY + TURN_SPEED_BOOST*rightX;
    	double leftMotors = STRAIGHT_RESTRICTER*leftY - TURN_SPEED_BOOST*rightX;
    	
    	if (rightMotors > 1) {
    		rightMotors = 1;
    		leftMotors = leftMotors/rightMotors;
    	} else if (rightMotors < -1) {
    		rightMotors = -1;
    		leftMotors = leftMotors/-rightMotors;
    	}
    	if (leftMotors > 1) {
    		leftMotors = 1;
    		rightMotors = rightMotors/leftMotors;
    	} else if (leftMotors < -1) {
    		leftMotors = -1;
    		rightMotors = rightMotors/-leftMotors;
    	}
    	
    	Robot.driveSubsystem.setMotors(leftMotors, rightMotors);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
