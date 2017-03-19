package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightToCommand extends Command implements PIDOutput {


	PIDController turnController;
	double rotateToAngleRate;
	double angle;
	
	double kP = .025;
	double kI = 0.0025;
	double kD = .0;
	double kF = 0;
	
	double kTolerance = 2.0;
	
	long zeroWaitTime;
	
	long startTime;
	boolean finisher;
	
	double distance;
	double speed;
//	double setpoint;
	
	
	public DriveStraightToCommand(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.driveSubsystem);
		this.distance = distance;
		this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {	
    	Robot.driveSubsystem.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.driveSubsystem.zeroEncoders( );
    	Robot.driveSubsystem.setMotors(0, 0);
    	turnController = new PIDController(kP, kI, kD, kF, Robot.driveSubsystem.ahrs, this);
    	turnController.setInputRange(-180.0f,  180.0f);
    	turnController.setOutputRange(-1.0, 1.0);
    	turnController.setAbsoluteTolerance(kTolerance);
    	turnController.setContinuous(true);
//    	setpoint = Robot.driveSubsystem.ahrs.getYaw();
    	turnController.setSetpoint(Robot.driveSubsystem.ahrs.getYaw());
//    	int count = 0;
//    	for(int i = 0; i < 30; i++){	
//    		if(turnController.getError() > .5 && count < 30){
//	        	turnController.setSetpoint(Robot.driveSubsystem.ahrs.getYaw());
//	        	count++;
//    		}
//    	}
    	turnController.enable();
    	Robot.driveSubsystem.setBreakMode(true);
    	
//    	Robot.driveSubsystem.setLeftRampRate(27.4285714286/2);
//    	Robot.driveSubsystem.setRightRampRate(27.4285714286/2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("Yaw: " + Robot.driveSubsystem.ahrs.getYaw() + "/t Setpoint: " + setpoint);
//    	System.out.println("Left: " + Robot.driveSubsystem.getDistance()[0] + "\tRight: " + Robot.driveSubsystem.getDistance()[1]);
//    	System.out.println("AVG Dist: " + Math.abs(Robot.driveSubsystem.getDistance()[0]) + Math.abs(Robot.driveSubsystem.getDistance()[1])/2 + "\tSetpoint: " + distance);
    	if(distance > 0) Robot.driveSubsystem.setMotors(rotateToAngleRate + speed, -rotateToAngleRate + speed);
    	else Robot.driveSubsystem.setMotors(-(-rotateToAngleRate + speed), -(rotateToAngleRate + speed));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(distance) <= (Math.abs(Robot.driveSubsystem.getDistance()[1]) + Math.abs(Robot.driveSubsystem.getDistance()[0]))/2;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.driveSubsystem.setLeftRampRate(0);
//    	Robot.driveSubsystem.setRightRampRate(0);
    	Robot.driveSubsystem.setMotors(0, 0);
    	Robot.driveSubsystem.zeroEncoders();
    	turnController.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	Robot.driveSubsystem.setLeftRampRate(0);
//    	Robot.driveSubsystem.setRightRampRate(0);
    	Robot.driveSubsystem.setMotors(0, 0);
    	Robot.driveSubsystem.zeroEncoders();
    	turnController.reset();
    }

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}