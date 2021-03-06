package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TimedTurnByCommand extends TimedCommand implements PIDOutput {

	PIDController turnController;
	double rotateToAngleRate;
	double angle;
	boolean finisher, checked = false;
	long finisherTime, startTime;
	
	double kP = 0.025 * 0.85;
	double kI = 0.0000;//0.00018
	double kD = 0.071 * 0.85;
	double kF = 0;
	
	double kTolerance = 1;
	
	long zeroWaitTime;
	
    public TimedTurnByCommand(double timeout, double angle) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveSubsystem);
        
		this.angle = angle;
		startTime = System.currentTimeMillis();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.zeroYaw();
    	Robot.driveSubsystem.changeControlMode(TalonControlMode.PercentVbus);
    	
    	Robot.driveSubsystem.setMotors(0, 0);

    	Robot.driveSubsystem.setBreakMode(true);
    	
    	turnController = new PIDController(kP, kI, kD, kF, Robot.driveSubsystem.ahrs, this);
    	turnController.setInputRange(-180.0f,  180.0f);
    	turnController.setOutputRange(-1.0, 1.0);
    	turnController.setAbsoluteTolerance(kTolerance);
    	turnController.setContinuous(true);
    	turnController.enable();
    	turnController.setSetpoint(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(System.currentTimeMillis());
    	Robot.driveSubsystem.setMotors(rotateToAngleRate, -rotateToAngleRate);
    }

    // Called once after timeout
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
    

	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}
