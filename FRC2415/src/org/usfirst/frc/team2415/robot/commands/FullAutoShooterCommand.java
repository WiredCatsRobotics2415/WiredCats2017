package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FullAutoShooterCommand extends CommandGroup {

    public FullAutoShooterCommand() {
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
    	
    	requires(Robot.shooterSubsystem);
    	requires(Robot.carouselSubsystem);
    	requires(Robot.feederSubsystem);
    	
    	addParallel(new ShooterCommand());
    	addParallel(new FeederCommand());
    	addParallel(new CarouselCommand());
    }
}
