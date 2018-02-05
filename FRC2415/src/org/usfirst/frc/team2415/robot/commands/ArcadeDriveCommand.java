package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import cheesy.CheesyDriveHelper;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDriveCommand extends Command {

	private CheesyDriveHelper cheesyDriveHelper = new CheesyDriveHelper();

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

		if (Robot.singlePlayerMode) {
			leftY = -Robot.operator.Y();
			rightX = Robot.operator.X();
		} else {
			leftY = -Robot.gamepad.leftY();
			rightX = Robot.gamepad.rightX();
		}

		boolean isQuickTurn = leftY < 0.1;

		Robot.driveSubsystem.setMotors(cheesyDriveHelper.cheesyDrive(leftY, rightX, isQuickTurn, false));
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
