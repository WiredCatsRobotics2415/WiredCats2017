
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.autocommands.StraightMiddleGearCommand;
import org.usfirst.frc.team2415.robot.commands.ClimberCommand;
import org.usfirst.frc.team2415.robot.commands.GroundGearCommand;
import org.usfirst.frc.team2415.robot.commands.ScoreSequenceCommand;
import org.usfirst.frc.team2415.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.GroundGearSubsystem;
import org.usfirst.frc.team2415.robot.utilities.GearButton;
import org.usfirst.frc.team2415.robot.utilities.WiredCatJoystick;
import org.usfirst.frc.team2415.robot.utilities.XBoxOneGamepad;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static ClimberSubsystem climberSubsystem;
	public static DriveSubsystem driveSubsystem;
	public static GroundGearSubsystem groundGearSubsystem;

	private Compressor compressor;
	
	public static DataSender dataSender;
  
	public static XBoxOneGamepad gamepad;
	public static WiredCatJoystick operator;
	public static GearButton gearButton;

	public static boolean singlePlayerMode = true;
	
	SendableChooser autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
		driveSubsystem = new DriveSubsystem();
		climberSubsystem = new ClimberSubsystem();
		groundGearSubsystem = new GroundGearSubsystem();
		
		compressor = new Compressor(RobotMap.PCM_ID);
		
//		dataSender = new DataSender("10.80.8.168", 9102); //COMPETITION BOT
//		dataSender = new DataSender("10.0.69.40", 9102); //MULE BOT
//		dataSender = new DataSender("10.4.20.40", 9102); //PRACTICE BOT
		

		driveSubsystem.zeroYaw();
		driveSubsystem.zeroEncoders();
//		groundGearSubsystem.limpDick();
		
		gamepad = new XBoxOneGamepad(0);
		gearButton = new GearButton();

		if(!singlePlayerMode) operator = new WiredCatJoystick(1);

		if(singlePlayerMode){
			gamepad.leftJoystick.whileHeld(new ClimberCommand());
			gamepad.rightJoystick.whileHeld(new ClimberCommand());
			gamepad.leftBumper.whileHeld(new ScoreSequenceCommand());
			gamepad.leftBumper.whenReleased(new GroundGearCommand(groundGearSubsystem.CARRY, 0));
			gamepad.rightBumper.whileHeld(new GroundGearCommand(groundGearSubsystem.GROUND, -1));
			gamepad.rightBumper.whenReleased(new GroundGearCommand(groundGearSubsystem.CARRY, 0));
			gamepad.a_button.whileHeld(new GroundGearCommand(groundGearSubsystem.GROUND, 0.1));
			
		} else {
			operator.buttons[3].whileHeld(new ClimberCommand());
			operator.buttons[11].whileHeld(new GroundGearCommand(groundGearSubsystem.GROUND, -1));
			operator.buttons[1].whileHeld(new GroundGearCommand(groundGearSubsystem.CARRY, -0.25));
			operator.buttons[10].whileHeld(new GroundGearCommand(groundGearSubsystem.GROUND, 0.25));
			operator.buttons[2].whenPressed(new ScoreSequenceCommand());
		}
		//TODO: Attempt #1
//		gearButton.whenPressed(new ScoreSequenceCommand());
		
//		autoChooser = new SendableChooser();
//		autoChooser.addDefault("Middle Gear", new StraightMiddleGearCommand());
//		autoChooser.addObject("Left Gear", new LeftGearCommand());
//		autoChooser.addObject("Right Gear", new RightGearCommand());
//		SmartDashboard.putData("Auto chooser", autoChooser);

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

//		driveSubsystem.zeroYaw();
		driveSubsystem.zeroEncoders();
		groundGearSubsystem.raiseIntake();
		
//		Command auto = new RightGearCommand();
//		Command auto = new LeftGearCommand();
		Command auto = new StraightMiddleGearCommand();
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
		groundGearSubsystem.raiseIntake();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
//		System.out.println(Robot.groundGearSubsystem.rightIR.get());

// 		//TODO: Attempt #2 (if work delete state stuff)
// 		if(groundGearSubsystem.getButton()){
// 			Command blueBanner = new ScoreSequenceCommand();
// 			blueBanner.start();
// 		}
		
//		//TODO: Attempt #3 (make sure you uncomment default command):
//		if(groundGearSubsystem.getButton()){
//			groundGearSubsystem.intakeState = groundGearSubsystem.GROUND;
//			Command blueBanner = new DriveStraightToCommand(-0.5, .2);
//			blueBanner.start();
//		}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
