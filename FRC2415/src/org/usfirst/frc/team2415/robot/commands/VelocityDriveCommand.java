package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

// TODO:
// failure mode (if encoder falls out or smth)
public class VelocityDriveCommand extends Command {

	// interpolation up maybe
	// overpower down to 0.5

	private double STRAIGHT_INTERPOLATION_FACTOR = 0.60;
	private double TURNING_INTERPOLATION_FACTOR = .3; // needs to be decided on
														// by Nathan
	private double DEADBAND = 0.1;
	private double FORWARD_STRAIGHT_RESTRICTER = 1;
	private double FORWARD_TURN_SPEED_BOOST = 0.4;
	private double BACKWARD_STRAIGHT_RESTRICTER = 1;
	private double BACKWARD_TURN_SPEED_BOOST = 0.4;
	private double overPower = .750; // .55
	private boolean pointTurn;
	private boolean brake;
	public double left, right;
	
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

		pointTurn = Math.abs(leftY) <= .1 && Math.abs(rightX) >= .1;
		// Hailey changed from Deadband

		// if(Robot.operator.buttons[10].get()) {
		// Robot.driveSubsystem.setMotors(300, 300);
		// return;
		// }
		
	if (!pointTurn) {

			Robot.driveSubsystem.setBreakMode(true);

			if (Math.abs(leftY) < DEADBAND)
				leftY = 0;
			if (Math.abs(rightX) < DEADBAND)
				rightX = 0;

			leftY = STRAIGHT_INTERPOLATION_FACTOR * Math.pow(leftY, 3) + (1 - STRAIGHT_INTERPOLATION_FACTOR) * leftY;
			rightX = TURNING_INTERPOLATION_FACTOR * Math.pow(rightX, 3) + (1 - TURNING_INTERPOLATION_FACTOR) * rightX;


			if (leftY >= 0) {
				left = FORWARD_STRAIGHT_RESTRICTER * leftY - FORWARD_TURN_SPEED_BOOST * rightX;
				right = FORWARD_STRAIGHT_RESTRICTER * leftY + FORWARD_TURN_SPEED_BOOST * rightX;
			} else {
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

			// if (rightX <= 0.1 && (-1040 * left) /
			// Robot.driveSubsystem.getVelocity()[0] < -.05) {
			// Robot.driveSubsystem.changeControlMode(TalonControlMode.PercentVbus);
			//
			// Robot.driveSubsystem.setMotors(0, 0);
			// System.out.println("Brakes on!");

			// }
			//
			// else if (Math.abs(Robot.driveSubsystem.getOutputVoltage()[0]) > 0
			// && rightX < 0.1) {
			// Robot.driveSubsystem.changeControlMode(TalonControlMode.Speed);
			//
			// double leftRate = 27.4285714286;
			// Robot.driveSubsystem.setLeftRampRate(leftRate);
			//
			// double rightRate = 27.4285714286;
			// Robot.driveSubsystem.setRightRampRate(rightRate);
			//
			// } else {
			// Robot.driveSubsystem.changeControlMode(TalonControlMode.Speed);
			// Robot.driveSubsystem.setLeftRampRate(0);
			// Robot.driveSubsystem.setRightRampRate(0);
			// }
			

//			 if ((Math.abs(Robot.driveSubsystem.getVelocity()[0]+Robot.driveSubsystem.getVelocity()[1])/2) < 5) {
//				Robot.driveSubsystem.setPIDF(Robot.driveSubsystem.leftTalFront, 0, 0, 0, 0.15);
//				Robot.driveSubsystem.setPIDF(Robot.driveSubsystem.rightTalFront, 0, 0, 0, 0.144);
//				Robot.driveSubsystem.setBreakMode(false);
			 if (Math.abs((1040 / 12 * Robot.driveSubsystem.getBusVoltage() * left)/(Robot.driveSubsystem.getVelocity()[0]+.001)) < 0.5 && 
						rightX < .3) {
				 System.out.println("brake mode pls work");
				Robot.driveSubsystem.setPIDF(Robot.driveSubsystem.leftTalFront, 0, 0, 0, 0);
				Robot.driveSubsystem.setPIDF(Robot.driveSubsystem.rightTalFront, 0, 0, 0, 0);
				Robot.driveSubsystem.setBreakMode(true);
			} else {
				Robot.driveSubsystem.setPIDF(Robot.driveSubsystem.leftTalFront, 0.1696969696 * 1.25, 0, 0, 0.15);
				Robot.driveSubsystem.setPIDF(Robot.driveSubsystem.rightTalFront, 0.1696969696 * 1.25, 0, 0, 0.144);
				Robot.driveSubsystem.setBreakMode(false);
			}
			 
			if (Robot.gamepad.rightJoystick.get() || Robot.gamepad.leftJoystick.get()) {
				Robot.driveSubsystem.setMotors(-1040 / 12 * Robot.driveSubsystem.getBusVoltage() * left * .65,
											   .65 * -1040 / 12 * Robot.driveSubsystem.getBusVoltage() * right);
			} else {
				Robot.driveSubsystem.setMotors(-1040 / 12 * Robot.driveSubsystem.getBusVoltage() * left,
											   -1040 / 12 * Robot.driveSubsystem.getBusVoltage() * right);
			}
			
			System.out.println("L SET: " + -1040 / 12 * Robot.driveSubsystem.getBusVoltage() * left + "\t R SET: "
					+ -1040 / 12 * Robot.driveSubsystem.getBusVoltage() * right);
			System.out.println("L SPEED:" + Robot.driveSubsystem.getVelocity()[0] + "\t R SPEED: "
					+ Robot.driveSubsystem.getVelocity()[1]);
			System.out.println("L ERROR: " + Robot.driveSubsystem.getError()[0] + "\t R ERROR: "
					+ Robot.driveSubsystem.getError()[1]);
			System.out.println(((1040 / 12 * Robot.driveSubsystem.getBusVoltage() * left)/(Robot.driveSubsystem.getVelocity()[0]+.001)));
	}

			else {
				Robot.driveSubsystem.setPIDF(Robot.driveSubsystem.leftTalFront, 0.1696969696 * 1.25, 0, 0, 0.15);
				Robot.driveSubsystem.setPIDF(Robot.driveSubsystem.rightTalFront, 0.1696969696 * 1.25, 0, 0, 0.144);
				Robot.driveSubsystem.setMotors(.87 * 1079 * rightX, -.87 * 1079 * rightX);
				Robot.driveSubsystem.setBreakMode(true);
			} 
			
			// StreamerPacket data = new StreamerPacket("driveData");
			// data.addAttribute("leftSpeed",
			// Robot.driveSubsystem.getVelocity()[0]);
			// data.addAttribute("rightSpeed",
			// Robot.driveSubsystem.getVelocity()[1]);
			// data.addAttribute("leftPosition",
			// Robot.driveSubsystem.getDistance()[0]);
			// data.addAttribute("rightPosition",
			// Robot.driveSubsystem.getDistance()[1]);
			// Robot.dataSender.send(data);

			// If the RPM is less than 50 then the robot is considered not
			// moving
			Robot.driveSubsystem.isMoving = Robot.driveSubsystem.getVelocity()[0] > 50
					|| Robot.driveSubsystem.getVelocity()[1] > 50;

		
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
