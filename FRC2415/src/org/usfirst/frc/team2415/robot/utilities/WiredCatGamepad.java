package org.usfirst.frc.team2415.robot.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class WiredCatGamepad extends Joystick{
	
	/*HUGE NOTE!!!!!
	 * Make sure that the controller is in DirectInput mode
	 * (flip the switch in back to "D")
	 * */
	
	
	public JoystickButton 	a_button, b_button,
							x_button, y_button;
	
	public JoystickButton leftBumper, rightBumper;
	
	public JoystickButton leftTrigger, rightTrigger;
	
	public WiredCatGamepad(int port) {
		super(port);
		
		a_button = new JoystickButton(this, 1);
		b_button = new JoystickButton(this, 2);
		x_button = new JoystickButton(this, 3);
		y_button = new JoystickButton(this, 4);
		
		
		
		leftBumper = new JoystickButton(this, 5);
		rightBumper = new JoystickButton(this, 6);	
	}
	
	public double leftY(){
		return this.getRawAxis(1);
	}
	
	public double leftX(){
		return this.getRawAxis(0);
	}
	
	public double rightY(){
		return this.getRawAxis(5);
	}
	
	public double rightX(){
		return this.getRawAxis(4);
	}
	
	public double leftTrigger(){
		return this.getRawAxis(3);
	}
	
	public double rightTrigger(){
		return this.getRawAxis(2);
	}
}