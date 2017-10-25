package org.usfirst.frc.team2415.robot.utilities;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class XBoxOneGamepad extends Joystick {

	/*
	 * HUGE NOTE!!!!! Make sure that the controller is in DirectInput mode (flip
	 * the switch in back to "D")
	 */

	public JoystickButton a_button, b_button, x_button, y_button;

	public JoystickButton leftBumper, rightBumper;

	public JoystickButton leftTrigger;

	public JoystickButton leftJoystick, rightJoystick;

	public LeftTriggerButton leftTriggerButton;
	public RightTriggerButton rightTriggerButton;

	public XBoxOneGamepad(int port) {
		super(port);

		a_button = new JoystickButton(this, 1);
		b_button = new JoystickButton(this, 2);
		x_button = new JoystickButton(this, 3);
		y_button = new JoystickButton(this, 4);

		leftBumper = new JoystickButton(this, 5);
		rightBumper = new JoystickButton(this, 6);

		leftJoystick = new JoystickButton(this, 9);
		rightJoystick = new JoystickButton(this, 10);
		
		leftTriggerButton = new LeftTriggerButton();
		rightTriggerButton = new RightTriggerButton();
	}

	public double leftY() {
		return -this.getRawAxis(1);
	}

	public double leftX() {
		return this.getRawAxis(0);
	}

	public double rightY() {
		return -this.getRawAxis(5);
	}

	public double rightX() {
		return this.getRawAxis(4);
	}

	public double leftTrigger() {
		return this.getRawAxis(2);
	}

	public double rightTrigger() {
		return this.getRawAxis(3);
	}

	public void rumbleLeft(double value) {
		this.setRumble(RumbleType.kLeftRumble, Math.abs(value));
	}

	public void rumbleRight(double value) {
		this.setRumble(RumbleType.kRightRumble, Math.abs(value));
	}

	public class RightTriggerButton extends Button {

		public boolean get() {
			return rightTrigger() > .5;
		}
	}
	
	public class LeftTriggerButton extends Button {

		public boolean get() {
			return leftTrigger() > .5;
		}
	}
}