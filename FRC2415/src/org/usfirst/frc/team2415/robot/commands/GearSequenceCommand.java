package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearSequenceCommand extends Command {

	// make outtake only start outtaking after 0.25 seconds
	
	boolean hasGear = false,
			finisher = false,
			intaking = false,
			dropping = false,
			autodrop = false,
			state; //true is up, down is false
	long startTime, outtakeTime, dropTime;
	double speed;
	
    public GearSequenceCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.groundGearSubsystem);
    }
    
    public GearSequenceCommand(boolean autodrop){
    	requires(Robot.groundGearSubsystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	System.out.println("Right Bumper: " + Robot.gamepad.rightBumper.get());
    	
    	if (Robot.gamepad.leftTriggerButton.get()){
    		speed = 1;
    	}
    	
    	if (Robot.gamepad.rightBumper.get()) {
    		state = false;
    		speed = -.75;
    		if (Robot.groundGearSubsystem.getIR()) {
    			hasGear = true;
    		} else {
    			hasGear = false;
    		}
    		
    		if (!hasGear) {
    			startTime = System.currentTimeMillis() / 1000;
    		}
    		
    		if (System.currentTimeMillis() / 1000 - startTime >= 0.05
    				&& Robot.groundGearSubsystem.getIR()) {
    			state = true;
    			speed = 0;
    		}
    	}
    	if (Robot.gamepad.rightTriggerButton.get()) {
    		state = false;
    		speed = -.75;
    		
    		if (Robot.groundGearSubsystem.getIR()) {
    			hasGear = true;
    		} else {
    			hasGear = false;
    		}
    		
    		if (!hasGear) {
    			startTime = System.currentTimeMillis() / 1000;
    		}
    		
    		if (System.currentTimeMillis() / 1000 - startTime >= 0.05
    				&& Robot.groundGearSubsystem.getIR()) {
    			speed = -0.3;
    		}
    		
    	} 
    	
    	if (Robot.gamepad.leftBumper.get()) {
    		state = false;
//        	System.out.println("Dropping in loop:" + dropping);
    		if(!dropping){
    			startTime = System.currentTimeMillis() / 1000;
    			dropping = true;
    		}
    		System.out.println("StartTime: " + startTime*10 + "\tCurrentTime: " + (System.currentTimeMillis() / 100) + "\tTime Diff: " + (System.currentTimeMillis() / 100 - startTime*10));
    		if(System.currentTimeMillis() / 100 - startTime*10 >= 5) speed = 0.35;
    		else speed = 0;
    	}
    	
    	if (Robot.gamepad.rightTriggerButton.get() && Robot.gamepad.leftBumper.get()) {
    		state = false;
        	speed = 0.35;
    	}
    	
    	//NEW
    	if (!Robot.gamepad.rightTriggerButton.get() && !Robot.gamepad.leftBumper.get() && !Robot.gamepad.rightBumper.get() && !Robot.gamepad.leftTriggerButton.get()){
    		state = true;
    		speed = 0;
    	}
    	
    	//OLD
//    	if (!Robot.gamepad.rightTriggerButton.get() && !Robot.gamepad.leftBumper.get() && !Robot.gamepad.rightBumper.get()){
//    		state = true;
//    		speed = 0;
//    	}
    	
//    	System.out.println("Dropping out of loop:" + dropping);
    	
    	dropping = Robot.gamepad.leftBumper.get();
    	
//    	if (autodrop) {
//    		state = false;
////        	System.out.println("Dropping in loop:" + dropping);
//    		System.out.println("StartTime: " + startTime + "\tCurrentTime: " + (System.currentTimeMillis() / 1000));
//    		if(System.currentTimeMillis() / 1000 - startTime >= 0.1) speed = 0.35;
//    		else speed = 0;
//    	}
    	
    	Robot.groundGearSubsystem.setMotor(-speed);
    	if (state) Robot.groundGearSubsystem.raiseIntake();
    	else Robot.groundGearSubsystem.dropIntake();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.groundGearSubsystem.raiseIntake();
    	Robot.groundGearSubsystem.setMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.groundGearSubsystem.setMotor(0);
    	Robot.groundGearSubsystem.raiseIntake();
    }
}
