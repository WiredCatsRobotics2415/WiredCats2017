package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.commands.GearOuttakeCommand;
import org.usfirst.frc.team2415.robot.commands.ScoreSequenceCommand;
import org.usfirst.frc.team2415.robot.commands.ZeroEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightGearCommand extends CommandGroup {

	double  leftLength = 0,
			fieldLength = 27,
			a1 = 0;
	
    public RightGearCommand() {
    	
    	addSequential(new DriveStraightToCommand(76/12, 0.420, 6));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new TimedTurnByCommand(1.5, -64));
    	addSequential(new ZeroEncoders());
    	addParallel(new ZeroEncoders());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new DriveStraightToCommand(3, 0.420/2, 2));
    	addParallel(new ZeroEncoders());
    	addSequential(new WaitCommand(0.5));
    	addParallel(new GearOuttakeCommand(0.1));
    	addSequential(new DriveStraightToCommand(-3, 0.4, 3));
    
    }
}
