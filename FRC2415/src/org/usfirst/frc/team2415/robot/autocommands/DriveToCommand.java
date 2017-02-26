package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToCommand extends Command {
	
	double distance;

    public DriveToCommand(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.zeroEncoders();
    	System.out.println("INIT");
    	Robot.driveSubsystem.changeControlMode(TalonControlMode.Position);
    	Robot.driveSubsystem.setMotors(distance, distance);
    	Robot.driveSubsystem.setBreakMode(true);
    	Robot.driveSubsystem.setTalonLimits(0.15);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Left: " + Robot.driveSubsystem.getDistance()[0] + "\tRight: " + Robot.driveSubsystem.getDistance()[1]);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("END");
    	Robot.driveSubsystem.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.driveSubsystem.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("INTU");
    	Robot.driveSubsystem.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.driveSubsystem.stopMotors();
    }
}
