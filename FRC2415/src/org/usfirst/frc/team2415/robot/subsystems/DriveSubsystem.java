package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.VelocityDriveCommand;
import org.usfirst.frc.team2415.robot.utilities.PixyCam;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.StatusFrameRate;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
	private CANTalon leftTalBack, leftTalFront, rightTalBack, rightTalFront;
	private AHRS ahrs;
	private PixyCam pixy;
	
	double WHEEL_RADIUS;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new VelocityDriveCommand());
    }
    
    public DriveSubsystem() {
    	
    	try {
            /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
            /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
            /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
            ahrs = new AHRS(SerialPort.Port.kMXP); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
        }
    	
    	leftTalBack = new CANTalon(RobotMap.LEFT_TALON_BACK);
    	leftTalFront = new CANTalon(RobotMap.LEFT_TALON_FRONT);
    	rightTalBack = new CANTalon(RobotMap.RIGHT_TALON_BACK);
    	rightTalFront = new CANTalon(RobotMap.RIGHT_TALON_FRONT);

    	leftTalFront.changeControlMode(TalonControlMode.Follower);
    	leftTalFront.set(leftTalBack.getDeviceID());
    	rightTalFront.changeControlMode(TalonControlMode.Follower);
    	rightTalFront.set(rightTalBack.getDeviceID());
    	
    	leftTalBack.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	rightTalBack.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	
    	leftTalBack.setStatusFrameRateMs(StatusFrameRate.Feedback, 20);
    	rightTalBack.setStatusFrameRateMs(StatusFrameRate.Feedback, 20);
    	
    	
    	
    	
    }
    
    public void stopMotors() {
    	leftTalBack.set(0);
    	rightTalBack.set(0);
    }
    
    public void setMotors(double left, double right) {
     	leftTalBack.set(-left);
     	rightTalBack.set(right);
    }
    
    public void changeControlMode(TalonControlMode mode){
    	leftTalBack.changeControlMode(mode);
    	rightTalBack.changeControlMode(mode);
    	
    	if(mode == TalonControlMode.Speed){
    		//TODO: see 12.4
    		leftTalBack.configNominalOutputVoltage(0, 0);
    		leftTalBack.configPeakOutputVoltage(12, -12);
    		setPIDF(leftTalBack, .1696969696, 0, 0, .149853516420);

    		rightTalBack.configNominalOutputVoltage(0, 0);
    		rightTalBack.configPeakOutputVoltage(12, -12);
    		setPIDF(rightTalBack, .1696969696, 0, 0, .149853516420);
    		
    		//0.154488160438 old f
    	}
    }
    
    public void setPIDF(CANTalon talon, double kP, double kI, double kD, double kF){
    	talon.setProfile(0);
    	talon.setPID(kP, kI, kD);
    	talon.setF(kF);
    }
    
    public double getPitch(){
    	return ahrs.getPitch();
    }
    
    public double getYaw(){
    	return ahrs.getYaw();
    }
    
    public double getRoll(){
    	return ahrs.getRoll();
    }
    
    public double getAngle(){
    	return ahrs.getAngle();
    }
    
    public double fPS2RPM(double fps){
    	return (fps*60)/(2*Math.PI*WHEEL_RADIUS);
    }
    
    public double[] getDistance(){
    	return new double[]{0,0};
    }
    
    public double[] getVelocity(){
    	return new double[]{leftTalBack.getSpeed(), rightTalBack.getSpeed()};
    }
    
    public double[] getError(){
    	return new double[]{leftTalBack.getClosedLoopError(), rightTalBack.getClosedLoopError()};
    }
    
    public void updateStatus() {
    	
    }
   
}

