package org.usfirst.frc.team2415.robot.utilities;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class GearButton extends Trigger {

    public boolean get() {
        return Robot.groundGearSubsystem.getButton();
    }
}
