package org.usfirst.frc.team2415.robot.commands;

import java.io.BufferedWriter;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.StreamerPacket;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VelocityDriveCommand extends Command {

	private double STRAIGHT_INTERPOLATION_FACTOR = 0.65;
	private double TURNING_INTERPOLATION_FACTOR = .2; //needs to be decided on by Nathan
	private double DEADBAND = 0.05;
	private double FORWARD_STRAIGHT_RESTRICTER = 1;
	private double FORWARD_TURN_SPEED_BOOST = 0.30;
	private double BACKWARD_STRAIGHT_RESTRICTER = 1;
	private double BACKWARD_TURN_SPEED_BOOST = 0.50;
	private double overPower = .5;
	private boolean pointTurn;

	public VelocityDriveCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveSubsystem.setBreakMode(true);
		Robot.driveSubsystem.changeControlMode(TalonControlMode.Speed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double leftY = Robot.gamepad.leftY();
		double rightX = Robot.gamepad.rightX();

		pointTurn = Math.abs(leftY) <= .1;
		//Hailey changed from Deadband

		if (pointTurn) {
			Robot.driveSubsystem.setMotors(.6 * 1079 * rightX, -.6 * 1079 * rightX);
			Robot.driveSubsystem.setBreakMode(true);

		} else {

			Robot.driveSubsystem.setBreakMode(false);
			
			if (Math.abs(leftY) < DEADBAND)
				leftY = 0;
			if (Math.abs(rightX) < DEADBAND)
				rightX = 0;

			leftY = STRAIGHT_INTERPOLATION_FACTOR * Math.pow(leftY, 3) + (1 - STRAIGHT_INTERPOLATION_FACTOR) * leftY;
			rightX = TURNING_INTERPOLATION_FACTOR * Math.pow(rightX, 3) + (1-TURNING_INTERPOLATION_FACTOR) * rightX;

			double left;
			double right;
			
			if (leftY >= 0) {
				left = FORWARD_STRAIGHT_RESTRICTER * leftY - FORWARD_TURN_SPEED_BOOST * rightX;
				right = FORWARD_STRAIGHT_RESTRICTER * leftY + FORWARD_TURN_SPEED_BOOST * rightX;
			}else {
				left = BACKWARD_STRAIGHT_RESTRICTER * leftY - BACKWARD_TURN_SPEED_BOOST * rightX;
				right = BACKWARD_STRAIGHT_RESTRICTER * leftY + BACKWARD_TURN_SPEED_BOOST * rightX;
			}

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

			double leftRate = Robot.driveSubsystem.getVelocity()[0]/(1079*left) <= 0 && (rightX < 0.5) ? 0 : 27.4285714286;
			Robot.driveSubsystem.setLeftRampRate(leftRate);
			
			double rightRate = -(Robot.driveSubsystem.getVelocity()[1]/(1079*right)) <= 0 && (rightX < 0.5) ? 0 : 27.4285714286;
			Robot.driveSubsystem.setRightRampRate(rightRate);
			
			if(Robot.gamepad.leftBumper.get()) Robot.driveSubsystem.setMotors(-0.65* 1079 * left, -0.65* 1079 * right);
			else Robot.driveSubsystem.setMotors(-2 * 1079 * left, -2 * 1079 * right);

			
//			 StreamerPacket data = new StreamerPacket("driveData");
//			 data.addAttribute("leftSpeed", Robot.driveSubsystem.getVelocity()[0]);
//			 data.addAttribute("rightSpeed", Robot.driveSubsystem.getVelocity()[1]);
//			 data.addAttribute("leftPosition", Robot.driveSubsystem.getDistance()[0]);
//			 data.addAttribute("rightPosition", Robot.driveSubsystem.getDistance()[1]);
//			 Robot.dataSender.send(data);

			// If the RPM is less than 50 then the robot is considered not
			// moving
			Robot.driveSubsystem.isMoving = Robot.driveSubsystem.getVelocity()[0] > 50
					|| Robot.driveSubsystem.getVelocity()[0] > 50;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveSubsystem.setMotors(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveSubsystem.setMotors(0, 0);
	}
}
