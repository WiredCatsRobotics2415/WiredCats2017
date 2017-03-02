package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ChainAutoTest extends CommandGroup {

    public ChainAutoTest() {
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
    	
    	requires(Robot.driveSubsystem);
    	
    	addSequential(new DriveStraightToCommand(10));
    	addSequential(new WaitCommand(1));
    	addSequential(new TurnToCommand(90));
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveStraightToCommand(10));
    	addSequential(new WaitCommand(1));
    	addSequential(new TurnToCommand(90));
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveStraightToCommand(10));
    	addSequential(new WaitCommand(1));
    	addSequential(new TurnToCommand(90));
    	
    }
}
