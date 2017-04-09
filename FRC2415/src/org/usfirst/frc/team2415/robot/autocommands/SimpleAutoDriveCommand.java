package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SimpleAutoDriveCommand extends Command {

	double speed;
	
    public SimpleAutoDriveCommand(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
		this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.driveSubsystem.setBreakMode(true);
    	Robot.driveSubsystem.setMotors(speed, speed);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
