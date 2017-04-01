
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.autocommands.StraightMiddleGearCommand;
import org.usfirst.frc.team2415.robot.commands.ClimberCommand;
import org.usfirst.frc.team2415.robot.commands.FullAutoShooterCommand;
import org.usfirst.frc.team2415.robot.commands.GroundGearCommand;
import org.usfirst.frc.team2415.robot.commands.HoldGearManipulatorFlapCommand;
//import org.usfirst.frc.team2415.robot.commands.ToggleGearPushingMechanismCommand;
import org.usfirst.frc.team2415.robot.subsystems.CarouselSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.FeederSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.GearManipulatorSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.GroundGearSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team2415.robot.utilities.WiredCatJoystick;
import org.usfirst.frc.team2415.robot.utilities.XBoxOneGamepad;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autoCommand;
	SendableChooser autoChooser;
	DriverStation driverStation;
	
	public static ShooterSubsystem shooterSubsystem;
	public static CarouselSubsystem carouselSubsystem;
	public static ClimberSubsystem climberSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static DriveSubsystem driveSubsystem;
	public static FeederSubsystem feederSubsystem;
	public static GearManipulatorSubsystem gearManipulatorSubsystem;
	public static GroundGearSubsystem groundGearSubsystem;

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

		
		
//		shooterSubsystem = new ShooterSubsystem();
//		intakeSubsystem = new IntakeSubsystem();
		driveSubsystem = new DriveSubsystem();
//		carouselSubsystem = new CarouselSubsystem();
		climberSubsystem = new ClimberSubsystem();
//		feederSubsystem = new FeederSubsystem();
//		gearManipulatorSubsystem = new GearManipulatorSubsystem();
		groundGearSubsystem = new GroundGearSubsystem();
		
		compressor = new Compressor(RobotMap.PCM_ID);
		
//		dataSender = new DataSender("10.80.8.168", 9102); //COMPETITION BOT
//		dataSender = new DataSender("10.0.69.40", 9102); //MULE BOT
//		dataSender = new DataSender("10.4.20.40", 9102); //PRACTICE BOT
		
//		autoChooser = new SendableChooser();
//		autoChooser.addDefault("Straight Middle Gear", new StraightMiddleGearCommand());
////		autoChooser.addObject("Right gear", new RightGearCommand());
//		autoChooser.addObject("Left gear", new LeftGearCommand());
//		SmartDashboard.putData("Auto command chooser", autoChooser);

		SmartDashboard.putData(Scheduler.getInstance());
//		SmartDashboard.putData("Left Gear Auto", new LeftGearCommand());
//		SmartDashboard.putData("Right Gear Auto", new RightGearCommand());
//		SmartDashboard.putData("Middle Gear Auto", new StraightMiddleGearCommand());

		driveSubsystem.zeroYaw();
		driveSubsystem.zeroEncoders();
		groundGearSubsystem.limpDick();
		
		gamepad = new XBoxOneGamepad(0);
		operator = new WiredCatJoystick(1);

//		gamepad.rightBumper.whileHeld(new IntakeCommand());

//		operator.buttons[1].whileHeld(new FullAutoShooterCommand());
//		operator.buttons[3].whileHeld(new ClimberCommand());
//		operator.buttons[6].whileHeld(new HoldGearManipulatorFlapCommand());
		
		operator.buttons[7].whileHeld(new GroundGearCommand(groundGearSubsystem.GROUND, -1));
		operator.buttons[1].whileHeld(new GroundGearCommand(groundGearSubsystem.CARRY, -0.1));
		operator.buttons[6].whileHeld(new  GroundGearCommand(groundGearSubsystem.GROUND, 1));
		
//		gamepad.a_button.whenPressed(new TimedTurnByCommand(3, 66));
//		gamepad.b_button.whileHeld(new TurnByCommand(65));
//		gamepad.y_button.whenPressed(new DriveStraightToCommand(94.625/12, 0.420));
		

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

		Robot.driveSubsystem.zeroYaw();
		Robot.driveSubsystem.zeroEncoders();
//		Robot.gearManipulatorSubsystem.setPushSolenoid(false); //backwards
//		Robot.gearManipulatorSubsystem.setManipSolenoid(true); //backwards
		
//		autoCommand = (Command) autoChooser.getSelected();
//		Robot.driveSubsystem.zeroYaw();
//		autoCommand.start();
		
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		

		// schedule the autonomous command (example)
		Command auto = new StraightMiddleGearCommand();
//		Robot.driveSubsystem.zeroYaw();
//		Robot.driveSubsystem.zeroEncoders();
		auto.start();

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

//		Robot.gearManipulatorSubsystem.setPushSolenoid(false); //backwards
//		Robot.gearManipulatorSubsystem.setManipSolenoid(true); //backwards

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println("Current: " + Robot.groundGearSubsystem.getCurrent());
//		SmartDashboard.putBoolean("less than 30", driverStation.getMatchTime() <= 30);
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	/**
	 * a function to run all of the update status functions in each of the
	 * subsystems
	 */
	public void updateStatus() {

	}
}
