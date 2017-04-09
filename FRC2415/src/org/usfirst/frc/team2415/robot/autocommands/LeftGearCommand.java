package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.commands.GearOuttakeCommand;
import org.usfirst.frc.team2415.robot.commands.ZeroEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftGearCommand extends CommandGroup {

    public LeftGearCommand() {
    	
    	addSequential(new DriveStraightToCommand(77.2/12, 0.420, 6));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new TimedTurnByCommand(1.5, 64.5));
    	addSequential(new ZeroEncoders());
    	addParallel(new ZeroEncoders());
    	addSequential(new WaitCommand(0.5));
//    	addSequential(new DistanceDriveCommand(3, 0.420/1.25));
    	addSequential(new DriveStraightToCommand(3, 0.420/2, 2)); //1.5
//    	addSequential(new SimpleAutoDriveCommand(0.2));
    	addParallel(new ZeroEncoders());
    	addSequential(new WaitCommand(0.5));
    	addParallel(new GearOuttakeCommand(0.1));
    	addSequential(new DriveStraightToCommand(-3, 0.4, 3));
    	
//    	addSequential(new WaitCommand(0.5));
//    	addSequential(new ZeroEncoders());
//    	addSequential(new DriveStraightToCommand(.5, 0.420/2));
        
    }
}
