package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.autocommands.SimpleAutoDriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScoreSequenceCommand extends CommandGroup {

    public ScoreSequenceCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	requires(Robot.groundGearSubsystem);
    	requires(Robot.driveSubsystem);
    	
//    	addParallel(new GroundGearCommand(Robot.groundGearSubsystem.GROUND, .1));
    	addParallel(new GearOuttakeCommand(.1));
    	addParallel(new SimpleAutoDriveCommand(0.4));

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
