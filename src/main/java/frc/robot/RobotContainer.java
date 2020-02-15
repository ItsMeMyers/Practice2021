/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

//import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.XboxController.Button;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Joysticks
  public final static Joystick rightStick = new Joystick(Constants.rightStick);
  public final static Joystick leftStick = new Joystick(Constants.leftStick);

  // Xbox Controller
  public final static XboxController gamepad = new XboxController(Constants.gamepad);

  public static TrajectoryConfig config;

  // Drivetrain
  public final static Drivetrain drivetrain = new Drivetrain();

  // Turret
  public final static Turret turret = new Turret();

  // Shooter
  public final static Shooter shooter = new Shooter();

  // Feeder 
  public final static Feeder feeder = new Feeder();

  // Intake
  public final static Intake intake = new Intake();

  // Limelight 
  public final static Limelight limelight = new Limelight();
  
  // Data Recorder
  public final static DataRecorder dataRecorder = new DataRecorder();

  // driveWithJoysticks
  public final static DriveWithJoysticks driveWithJoysticks = new DriveWithJoysticks(drivetrain, rightStick, leftStick);

  // recordData
  public final static RecordData recordDataSuccessful = new RecordData(dataRecorder, true);
  public final static RecordData recordDataUnsuccessful = new RecordData(dataRecorder, false);

  // targetEntity
  public final static TargetEntity targetEntity = new TargetEntity(limelight, turret);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Configure Each Button
    final JoystickButton aButton = new JoystickButton(gamepad, Button.kA.value);
    final JoystickButton yButton = new JoystickButton(gamepad, Button.kY.value);
    final JoystickButton bButton = new JoystickButton(gamepad, Button.kB.value);
    final JoystickButton xButton = new JoystickButton(gamepad, Button.kX.value);
    final JoystickButton rTrigger = new JoystickButton(gamepad, Axis.kRightTrigger.value);
    final JoystickButton rBumper = new JoystickButton(gamepad, Button.kBumperRight.value);
    final POVButton upButton = new POVButton(gamepad, Constants.povUp);
    final POVButton downButton = new POVButton(gamepad, Constants.povDown);

    bButton.whenPressed(recordDataSuccessful);
    xButton.whenPressed(recordDataUnsuccessful);
    aButton.whenPressed(new IntakeToggle(intake));
    yButton.whenPressed(new RunShooter(shooter, feeder, limelight, dataRecorder));
    rTrigger.whenHeld(new IntakeIn(intake));
    rBumper.whenHeld(new IntakeOut(intake));
    upButton.whenPressed(targetEntity);
    downButton.cancelWhenPressed(targetEntity);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.ksVolts,
                                       Constants.kvVoltSecondsPerMeter,
                                       Constants.kaVoltSecondsSquaredPerMeter),
            Constants.kDriveKinematics,
            10);
    // Creating a trajectory from PathWeaver
    //Trajectory pathWeaverTest = TrajectoryUtil.fromPathweaverJson(Path.get("/home/lvuser/deploy/Mid.wpilib.json"));

    // Create config for trajectory
     config =
        new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
                             Constants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint)
            //Doesn't reverse the trajectory
            .setReversed(false);

    // An example trajectory to follow.  All units in meters.
      Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these waypoints
        List.of(
            new Translation2d(2, 0),
            new Translation2d(5, 0)
        ),
        // End at this location
        new Pose2d(5, 0, new Rotation2d(0)),
        // Pass config
        config
    );

    RamseteCommand ramseteCommand = new RamseteCommand(
        trajectory, // We input our desired trajectory here
        drivetrain::getPose,
        new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
        new SimpleMotorFeedforward(Constants.ksVolts,
                                   Constants.kvVoltSecondsPerMeter,
                                   Constants.kaVoltSecondsSquaredPerMeter),
        Constants.kDriveKinematics,
        drivetrain::getWheelSpeeds,
        new PIDController(Constants.kPDriveVel, 0, 0),
        new PIDController(Constants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        drivetrain::tankDriveVolts,
        drivetrain
    );

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> drivetrain.tankDriveVolts(0, 0));
  }
}
