package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleGearManipulatorFlapCommand extends Command {

    public ToggleGearManipulatorFlapCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearManipulatorSubsystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!Robot.intakeSubsystem.isExtended){
        	if(Robot.gearManipulatorSubsystem.getManipSolenoid() == false)
        		Robot.gearManipulatorSubsystem.setPushSolenoid(false);
        	Robot.gearManipulatorSubsystem.toggleManipSolenoid();
        	Robot.gearManipulatorSubsystem.isExtended = !Robot.gearManipulatorSubsystem.getManipSolenoid();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
