package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class PathfinderCommand extends Command {

	
	Waypoint[] points = new Waypoint[] {
		    new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
		    new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
		    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
		};
	
	 double timestep = 0.02;
	 double maxVel = 3.6576;
	 double maxAccel = 2;
	 double maxJerk = 60.0 ;

	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
			timestep, maxVel, maxAccel, maxJerk);
	Trajectory trajectory = Pathfinder.generate(points, config);

	TankModifier modifier = new TankModifier(trajectory).modify(0.6349999998984);

	EncoderFollower left = new EncoderFollower(modifier.getLeftTrajectory());
	EncoderFollower right = new EncoderFollower(modifier.getRightTrajectory());

    public PathfinderCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	left.configureEncoder(Robot.driveSubsystem.getEncPosition()[0], 4096, Robot.driveSubsystem.WHEEL_CIRCUMFERENCE/Math.PI);
    	right.configureEncoder(Robot.driveSubsystem.getEncPosition()[1], 4096, Robot.driveSubsystem.WHEEL_CIRCUMFERENCE/Math.PI);
   
    	left.configurePIDVA(1, 0.0, 0.0, 0, 0);
    	right.configurePIDVA(1, 0.0, 0.0, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double l = left.calculate(Robot.driveSubsystem.getEncPosition()[0]);
    	double r = right.calculate(Robot.driveSubsystem.getEncPosition()[1]);

    	double gyro_heading = Robot.driveSubsystem.getYaw();    // Assuming the gyro is giving a value in degrees
    	double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

    	double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
    	double turn = 0.8 * (-1.0/80.0) * angleDifference;
    	
    	Robot.driveSubsystem.setMotors(l + turn, r - turn);
    	System.out.println("Left: " + (l+turn) + "\tRight: " + (r-turn));

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
