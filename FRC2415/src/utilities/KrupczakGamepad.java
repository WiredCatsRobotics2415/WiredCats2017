/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team2415.robot.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class KrupczakGamepad extends Joystick{
	
	/*
	 * MAKE SURE IT IS IN PC MODE
	 */
    
    public JoystickButton[] buttons = new JoystickButton[12];
    public JoystickButton trigger;
    
    public KrupczakGamepad(int port){
        super(port);
        
        for (int i = 1; i < buttons.length; i++){
        	buttons[i] = new JoystickButton(this, i);
        }
        
        trigger = new JoystickButton(this,1);
    }
    
    public double rightX(){
		return this.getRawAxis(0);
	}
	
	public double rightY(){
		return this.getRawAxis(1);
	}
	
	public double throttle(){
		return this.getRawAxis(2);
	}
	
	public double yaw(){
		return this.getRawAxis(3);
	}
	
	public double slider(){
		return this.getRawAxis(4);
	}
}