package org.usfirst.frc.team2415.robot.autocommands;

import java.util.LinkedList;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.utilities.FalconPathPlanner;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TrajectoryCommand extends Command {
	
	double[][] waypoints;
	double totalTime = 10; //max seconds we want to drive the path
	double timeStep = 0.02; //period of control loop on Rio, seconds
	double robotTrackWidth = 2.083333333; //distance between left and right wheels, feet
	FalconPathPlanner path;
	LinkedList<Double> left = new LinkedList<Double>();
	LinkedList<Double> right = new LinkedList<Double>();

    public TrajectoryCommand(double[][] path) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	waypoints = path;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	path = new FalconPathPlanner(waypoints);
    	path.calculate(totalTime, timeStep, robotTrackWidth);
    	for(double step[] : path.smoothLeftVelocity){
    		left.add(step[1]);
    	}
    	for(double step[] : path.smoothRightVelocity){
    		right.add(step[1]);
    	}
    	
    	Robot.driveSubsystem.changeControlMode(TalonControlMode.Speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
  
    	Robot.driveSubsystem.setMotors(Robot.driveSubsystem.fPS2RPM(left.removeFirst()), Robot.driveSubsystem.fPS2RPM(right.removeFirst()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return left.size() == 0 && right.size() == 0;
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
