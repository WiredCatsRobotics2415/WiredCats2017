
package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.StreamerPacket;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FeederCommand extends Command {

	double speedCap = 0.84;
	boolean checked = false;
	
    public FeederCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.feederSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.feederSubsystem.setSetpoint(0);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!checked){
    		if(!Robot.shooterSubsystem.rampedUp()) return;
    		checked = true;
    		Robot.feederSubsystem.enable();
    	}
    	
    	Robot.feederSubsystem.setSetpoint(4000);
    	
    	System.out.println("Feeder: " + Robot.feederSubsystem.getSpeed());
    	
    	StreamerPacket data = new StreamerPacket("feederData");
    	data.addAttribute("feederSpeed", Robot.feederSubsystem.getSpeed());
    	Robot.dataSender.send(data);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.feederSubsystem.setSetpoint(0);
    	checked = false;
    	Robot.feederSubsystem.getPIDController().reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.feederSubsystem.setSetpoint(0);
    	checked = false;
    	Robot.feederSubsystem.getPIDController().reset();
    }
}