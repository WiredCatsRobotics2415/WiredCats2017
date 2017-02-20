
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.commands.FullAutoShooterCommand;
import org.usfirst.frc.team2415.robot.commands.IntakeCommand;
import org.usfirst.frc.team2415.robot.commands.ToggleGearManipulatorFlapCommand;
import org.usfirst.frc.team2415.robot.subsystems.CarouselSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.FeederSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.GearManipulatorSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team2415.robot.utilities.WiredCatJoystick;
import org.usfirst.frc.team2415.robot.utilities.XBoxOneGamepad;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static ShooterSubsystem shooterSubsystem;
	public static CarouselSubsystem carouselSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static DriveSubsystem driveSubsystem;
	public static FeederSubsystem feederSubsystem;
	public static GearManipulatorSubsystem gearManipulatorSubsystem;

	private Compressor compressor;
	
	public static DataSender dataSender;
  
	public static XBoxOneGamepad gamepad;
	public static WiredCatJoystick operator;

	public static boolean singlePlayerMode = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		shooterSubsystem = new ShooterSubsystem();
		intakeSubsystem = new IntakeSubsystem();
		driveSubsystem = new DriveSubsystem();
		carouselSubsystem = new CarouselSubsystem();
		feederSubsystem = new FeederSubsystem();
		gearManipulatorSubsystem = new GearManipulatorSubsystem();
		
		compressor = new Compressor(RobotMap.PCM_ID);
		
		dataSender = new DataSender("10.80.8.168", 9102); //COMPETITION BOT


		
		gamepad = new XBoxOneGamepad(0);
		operator = new WiredCatJoystick(1);

		gamepad.rightBumper.whileHeld(new IntakeCommand());

		operator.buttons[1].whileHeld(new FullAutoShooterCommand());
		operator.buttons[6].toggleWhenPressed(new ToggleGearManipulatorFlapCommand());
		
//		LiveWindow.addActuator("feeder", "FeederTalon", feederSubsystem.getPIDController());

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		// Command automousCommand = new
		// TrajectoryCommand(Trajectories.CHEESY_PATH);
		// automousCommand.start();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
//		SmartDashboard.putNumber("Feeder Speed", feederSubsystem.getSpeed());
		LiveWindow.run();
	}

	/**
	 * a function to run all of the update status functions in each of the
	 * subsystems
	 */
	public void updateStatus() {

	}
}
