package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ZeroEncoders extends InstantCommand {

    public ZeroEncoders() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveSubsystem);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.driveSubsystem.zeroEncoders();
    	Robot.driveSubsystem.zeroEncoders();
    	Robot.driveSubsystem.zeroEncoders();
    }

}
