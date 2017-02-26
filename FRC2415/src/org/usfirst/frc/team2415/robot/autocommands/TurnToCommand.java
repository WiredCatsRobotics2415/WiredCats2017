package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToCommand extends Command implements PIDOutput {


	PIDController turnController;
	double rotateToAngleRate;
	double angle;
	boolean finisher, checked = false;
	long finisherTime, startTime;
	
	double kP = 0.01;
	double kI = 0.000101;
	double kD = 0.03;
	double kF = 0;
	
	double kTolerance = 1;
	
	long zeroWaitTime;
	

	
	public TurnToCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.driveSubsystem);
		this.angle = angle;
		startTime = System.currentTimeMillis();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.driveSubsystem.changeControlMode(TalonControlMode.PercentVbus);
    	
    	if(!checked){
    		if((System.currentTimeMillis() - startTime) < 750) return;
    		checked = true;
    	}
    	
    	Robot.driveSubsystem.zeroYaw();
    	Robot.driveSubsystem.setMotors(0, 0);

    	Robot.driveSubsystem.setBreakMode(true);
    	
    	turnController = new PIDController(kP, kI, kD, kF, Robot.driveSubsystem.ahrs, this);
    	turnController.setInputRange(-180.0f,  180.0f);
    	turnController.setOutputRange(-1.0, 1.0);
    	turnController.setAbsoluteTolerance(kTolerance);
    	turnController.setContinuous(true);
    	turnController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	turnController.setSetpoint(angle);
    	System.out.println("Yaw: " + Robot.driveSubsystem.getYaw() + "\tsetpoint: " + turnController.getSetpoint());
    	Robot.driveSubsystem.setMotors(rotateToAngleRate, -rotateToAngleRate);
//    	finisher = (Math.abs(turnController.getError()) <= kTolerance);
//    	if (finisher == false) finisherTime = System.currentTimeMillis();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	return System.currentTimeMillis() - finisherTime >= 3;
    	return turnController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("DONE");
    	Robot.driveSubsystem.setMotors(0, 0);
    	turnController.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("DONE");
    	Robot.driveSubsystem.setMotors(0, 0);
    	turnController.reset();
    }

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}