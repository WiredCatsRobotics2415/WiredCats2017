package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.commands.ScoreSequenceCommand;

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
    	
    	addSequential(new DriveStraightToCommand(109/12, 0.420));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new TimedTurnByCommand(1.5, -63));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new DriveStraightToCommand(1.1, 0.420/1.25));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new DriveStraightToCommand(.5, 0.420/2));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ScoreSequenceCommand());
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
