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
	
	public boolean isMoving;

	double WHEEL_DIAMETER = 3.25/12;


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

    	leftTalBack.changeControlMode(TalonControlMode.Follower);
    	leftTalBack.set(leftTalFront.getDeviceID());
    	rightTalBack.changeControlMode(TalonControlMode.Follower);
    	rightTalBack.set(rightTalFront.getDeviceID());
    	
    	leftTalFront.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	rightTalFront.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	
    	leftTalFront.setStatusFrameRateMs(StatusFrameRate.Feedback, 1);
    	rightTalFront.setStatusFrameRateMs(StatusFrameRate.Feedback, 1);
    	
    	leftTalFront.enableBrakeMode(false);
    	rightTalFront.enableBrakeMode(false);
    	
    	
    	
    }
    
    public void stopMotors() {
    	leftTalFront.set(0);
    	rightTalFront.set(0);
    }
    
    public void setMotors(double left, double right) {
     	leftTalFront.set(-left);
     	rightTalFront.set(right);
    }
    
    public void changeControlMode(TalonControlMode mode){
    	leftTalFront.changeControlMode(mode);
    	rightTalFront.changeControlMode(mode);
    	
    	if(mode == TalonControlMode.Speed){
    		//TODO: see 12.4
    		leftTalFront.configNominalOutputVoltage(0, 0);
    		leftTalFront.configPeakOutputVoltage(12, -12);

    		setPIDF(leftTalFront, .1696969696, 0, 0, 2/1000);

    		rightTalFront.configNominalOutputVoltage(0, 0);
    		rightTalFront.configPeakOutputVoltage(12, -12);
    		setPIDF(rightTalFront, .1696969696, 0, 0, 2/1000);

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
    	return (fps*60)/(Math.PI*WHEEL_DIAMETER);
    }
    
    public double[] getDistance(){
    	return new double[]{0,0};
    }
    
    public double[] getVelocity(){
    	return new double[]{leftTalFront.getSpeed(), rightTalFront.getSpeed()};
    }
    
    public double[] getError(){
    	return new double[]{leftTalFront.getClosedLoopError(), rightTalFront.getClosedLoopError()};
    }
    
    //no slip @ 27.4285714286
    public void setLeftRampRate(double rate){
		leftTalFront.setVoltageRampRate(rate);
    }
    
    public void setRightRampRate(double rate){
		rightTalFront.setVoltageRampRate(rate);
    }
    
    public double getVoltage(){
    	return  leftTalFront.getOutputVoltage()/leftTalFront.getBusVoltage();
    }
    
    public void updateStatus() {
    	
    }
   
}

