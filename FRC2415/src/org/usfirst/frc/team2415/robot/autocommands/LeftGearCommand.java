package org.usfirst.frc.team2415.robot.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftGearCommand extends CommandGroup {

	//start: left edge of robot 5 feet away from left side of field
	
	double leftLength = 8.55,
			fieldLength = 27,
			lineHeight = 10-2.25,
			dLeft = 8.55;
			
	
    public LeftGearCommand() {
    	addSequential(new ZeroEncodersCommand());
    	addSequential(new DriveStraightToCommand(97/12, 0.420));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new TimedTurnByCommand(1.5, 65));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ZeroEncodersCommand());
    	addSequential(new ZeroEncodersCommand());
    	addSequential(new DriveStraightToCommand(1.5, 0.420/1.25));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ZeroEncodersCommand());
    	addSequential(new DriveStraightToCommand(.5, 0.420/2));
//    	addSequential(new TurnByCommand(Math.atan((lineHeight-1)/(dLeft-leftLength))));
//    	addSequential(new WaitCommand(0.5));
//    	addSequential(new DriveStraightToCommand(Math.sqrt(Math.pow(dLeft-leftLength, 2) + Math.pow(lineHeight-1, 2)), 0.25));
//        addSequential(new TurnByCommand(-120));
//        addSequential(new WaitCommand(0.5));
//        addSequential(new DriveStraightToCommand(-1, 0.25));
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
