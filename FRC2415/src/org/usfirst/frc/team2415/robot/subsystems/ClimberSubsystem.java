package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	CANTalon climberTalon1;
	CANTalon climberTalon2;
	
	public ClimberSubsystem(){
		climberTalon1 = new CANTalon(RobotMap.CLIMBER_TALON);
		climberTalon2 = new CANTalon(RobotMap.CLIMBER_TALON2);
		
		climberTalon1.changeControlMode(TalonControlMode.Voltage);
		climberTalon2.changeControlMode(TalonControlMode.Voltage);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setMotor(double voltage){
    	climberTalon1.set(-voltage);
    	climberTalon2.set(voltage);
    }
    
    public double getVoltage(){
    	return climberTalon1.getOutputVoltage();
    }
    
    public double getMotor(){
    	return climberTalon1.get();
    }
}

