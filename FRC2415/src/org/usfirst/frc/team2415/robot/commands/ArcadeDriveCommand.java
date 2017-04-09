package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDriveCommand extends Command {
	
	private double INTERPOLATION_FACTOR = 0.420;
	private double DEADBAND = 0.05;
	private double STRAIGHT_RESTRICTER = 1; 
	private double TURN_SPEED_BOOST = 0.35;
	private double overPower = .6;

    public ArcadeDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.driveSubsystem.stopMotors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double leftY;
    	double rightX;
    	
    	if (!Robot.singlePlayerMode){
    		leftY = Robot.operator.Y();
    		rightX = Robot.operator.X();
    	}	else {
    		leftY = Robot.gamepad.leftY();
    		rightX = -Robot.gamepad.rightX();
    	}
    	
    	if (Math.abs(leftY) < DEADBAND) leftY = 0;
    	if (Math.abs(rightX) < DEADBAND) rightX = 0;
    	
    	leftY = INTERPOLATION_FACTOR*Math.pow(leftY, 3) + (1-INTERPOLATION_FACTOR)*leftY;
    	rightX = INTERPOLATION_FACTOR*Math.pow(rightX, 3) + (1-INTERPOLATION_FACTOR)*rightX;
    	
    	double left = STRAIGHT_RESTRICTER*leftY + TURN_SPEED_BOOST*rightX;
    	double right = STRAIGHT_RESTRICTER*leftY - TURN_SPEED_BOOST*rightX;
    	
        if (left > 1.0) {
            right -= overPower * (left - 1.0);
            left = 1.0;
        } else if (right > 1.0) {
            left -= overPower * (right - 1.0);
            right = 1.0;
        } else if (left < -1.0) {
            right += overPower * (-1.0 - left);
            left = -1.0;
        } else if (right < -1.0) {
            left += overPower * (-1.0 - right);
            right = -1.0;
        }
    	
//        if (Robot.gamepad.leftTrigger.get()) Robot.driveSubsystem.setMotors(-left *.5, -right*.5);
//        System.out.println("L VOLT: " + -left + "\t R VOLT: " + -right);
//        System.out.println("L SPEED:" + Robot.driveSubsystem.getVelocity()[0] + "R SPEED: " + Robot.driveSubsystem.getVelocity()[1]);
        Robot.driveSubsystem.setMotors(-left, -right);
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
    	Robot.driveSubsystem.stopMotors();
    }
}
