/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.RobotContainerConstants.gamepadPort;
import static frc.robot.Constants.RobotContainerConstants.leftStickPort;
import static frc.robot.Constants.RobotContainerConstants.povDown;
import static frc.robot.Constants.RobotContainerConstants.povUp;
import static frc.robot.Constants.RobotContainerConstants.rightStickPort;
import static frc.robot.Constants.RouteFinderConstants.kMaxAccelerationMetersPerSecondSquared;
import static frc.robot.Constants.RouteFinderConstants.kMaxSpeedMetersPerSecond;
import static frc.robot.Constants.RouteFinderConstants.kTrackwidthMeters;
import static frc.robot.Constants.RouteFinderConstants.kaVoltSecondsSquaredPerMeter;
import static frc.robot.Constants.RouteFinderConstants.ksVolts;
import static frc.robot.Constants.RouteFinderConstants.kvVoltSecondsPerMeter;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.*;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret; 

public class RobotContainer {
  // Subsystems should be private here and have to be passed to commands because it is better coding practice.

  // Joysticks
  public static final Joystick rightStick = new Joystick(rightStickPort);
  public static final Joystick leftStick = new Joystick(leftStickPort);
  public static final Joystick gamepad = new Joystick(gamepadPort);
  
  // Drivetrain
  public static final Drivetrain drivetrain = new Drivetrain();

  // Turret
  public static final Turret turret = new Turret();

  // Shooter
  public static final Shooter shooter = new Shooter();

  // Feeder
  public static final Feeder feeder = new Feeder();

  // Intake
  public static final Intake intake = new Intake();

  // Limelight
  public static final Limelight limelight = new Limelight();

  //Climber
  public static final Climber climber = new Climber();

  //Color Wheel
  //private static final ColorWheel colorwheel = new ColorWheel();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
     /**
     * COMMAND INFO From the official wpilib docs: "While users are able to create
     * commands by explicitly writing command classes (either by subclassing
     * CommandBase or implementing Command), for many commands (such as those that
     * simply call a single subsystem method) this involves a lot of wasteful
     * boilerplate code. To help alleviate this, many of the prewritten commands
     * included in the command-based library may be inlined - that is, the command
     * body can be defined in a single line of code at command construction."
     * 
     * TLDR: You shouldn't create a whole new file for a command that only calls one
     * method.
     * 
     * (I didn't know what these did so here is an explanation if anyone is
     * confused) Also method references and lambda expressions let you pass
     * subroutines as parameters Method reference: subsystem::method Lambda
     * expression: () -> subsystem.method() They essentially do the same thing
     */
    // Attaches a commmand to each button
    // Starts the shooter motors when the Y button is pressed
    //new JoystickButton(gamepad, Constants.Left_Bumper_Button).whenPressed(new RunShooter(shooter, feeder, limelight, gamepad));
    // Takes in balls from the ground when the right trigger is held
    // new JoystickButton(gamepad, Constants.Right_Bumper_Button).whenHeld(new IntakeIn(intake,gamepad));
    // new JoystickButton(gamepad, Constants.Right_Bumper_Button).whenReleased(new IntakeStop(intake,gamepad));
    // // Pushes out balls onto the ground when the right bumper is held
    // new JoystickButton(gamepad, Constants.Right_Trigger_Button).whenHeld(new IntakeOut(intake,gamepad));
    // new JoystickButton(gamepad, Constants.Right_Trigger_Button).whenReleased(new IntakeStop(intake,gamepad));
    //Run the feeder system
    // new JoystickButton(gamepad, Constants.Left_Trigger_Button).whenHeld(new FeederRun(feeder, intake, shooter, gamepad));
    // //Lift Intake pneau
    // new JoystickButton(gamepad, Constants.X_Button).whenPressed(new IntakeUp(intake));
    // //lower intake pneau
    // new JoystickButton(gamepad, Constants.Y_Button).whenPressed(new IntakeDown(intake));

    // new JoystickButton(gamepad, Constants.Left_Joystick_Pressed).whenHeld(new WithdrawClimber(climber, gamepad));

    // new JoystickButton(gamepad,Constants.Left_Joystick_Pressed).whenReleased(new StopClimber(climber));
    // // Starts targeting when the up arrow on the D-pad is pressed
    // new POVButton(gamepad, povUp).whenPressed(new DeployClimber(climber, gamepad));
    // // Ends targeting when the down arrow on the D-pad is pressed
    // new POVButton(gamepad, povDown).whenHeld(new ClearAllBalls(intake, feeder, shooter, gamepad));
    // new POVButton(gamepad, povDown).whenReleased(new StopBelts(intake, feeder, shooter, gamepad));
    //raise shooter angle
    // new POVButton(gamepad, povRight).whenPressed(new PancakeUp(shooter, gamepad));
    //lower shooter angle
    // new POVButton(gamepad, povLeft).whenPressed(new PancakeDown(shooter, gamepad));
    // Heads to a position when the left bumper is pressed
    // new JoystickButton(gamepad, Button.kBumperLeft.value)
    //     .whenPressed(RouteFinder.getPathCommand(RouteFinder.trajectorygen(pointx, pointy, rotation)));
    
    // Driving
    // new PerpetualCommand(new DriveTele(drivetrain, rightStick, leftStick)).schedule();
    // Turret
   // new PerpetualCommand(new MoveTurret(turret, gamepad)).schedule(); 
  }

  public static TrajectoryConfig getConfig() {
    var kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);
    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter), kDriveKinematics, 10);

    // Create config for trajectory
    return new TrajectoryConfig(kMaxSpeedMetersPerSecond, kMaxAccelerationMetersPerSecondSquared)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(kDriveKinematics)
        // Apply the voltage constraint
        .addConstraint(autoVoltageConstraint)
        // Doesn't reverse the trajectory
        .setReversed(false);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /* public Command getAutonomousCommand() {
    var kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

    // An example trajectory to follow. All units in meters.
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these waypoints
        List.of(new Translation2d(2, 0), new Translation2d(5, 0)),
        // End at this location
        new Pose2d(5, 0, new Rotation2d(0)),
        // Pass config
        getConfig());
 */
    /**
     * This declaration is fairly substantial, so we’ll go through it
     * argument-by-argument:
     * 
     * The trajectory: This is the trajectory to be followed; accordingly, we pass
     * the command the trajectory we just constructed in our earlier steps.
     * 
     * The pose supplier: This is a method reference (or lambda) to the drive
     * subsystem method that returns the pose. The RAMSETE controller needs the
     * current pose measurement to determine the required wheel outputs.
     * 
     * The RAMSETE controller: This is the RamseteController object (Java, C++) that
     * will perform the path-following computation that translates the current
     * measured pose and trajectory state into a chassis speed setpoint.
     * 
     * The drive feedforward: This is a SimpleMotorFeedforward object (Java, C++)
     * that will automatically perform the correct feedforward calculation with the
     * feedforward gains (kS, kV, and kA) that we obtained from the drive
     * characterization tool.
     * 
     * The drive kinematics: This is the DifferentialDriveKinematics object (Java,
     * C++) that we constructed earlier in our constants file, and will be used to
     * convert chassis speeds to wheel speeds.
     * 
     * The wheel speed supplier: This is a method reference (or lambda) to the drive
     * subsystem method that returns the wheel speeds
     * 
     * The left-side PIDController: This is the PIDController object (Java, C++)
     * that will track the left-side wheel speed setpoint, using the P gain that we
     * obtained from the drive characterization tool.
     * 
     * The right-side PIDController: This is the PIDController object (Java, C++)
     * that will track the right-side wheel speed setpoint, using the P gain that we
     * obtained from the drive characterization tool.
     * 
     * The output consumer: This is a method reference (or lambda) to the drive
     * subsystem method that passes the voltage outputs to the drive motors.
     * 
     * The robot drive: This is the drive subsystem itself, included to ensure the
     * command does not operate on the drive at the same time as any other command
     * that uses the drive.
     */
//     RamseteCommand ramseteCommand = new RamseteCommand(trajectory, // We input our desired trajectory here
//         drivetrain::getPose, new RamseteController(kRamseteB, kRamseteZeta),
//         new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter), kDriveKinematics,
//         drivetrain::getWheelSpeeds, new PIDController(kPDriveVel, 0, 0), new PIDController(kPDriveVel, 0, 0),
//         // RamseteCommand passes volts to the callback
//         drivetrain::tankDriveVolts, drivetrain);

//     // Reset odometry, then run path following command, then stop at the end.
//     return ramseteCommand.beforeStarting(drivetrain::resetOdometry).andThen(() -> drivetrain.tankDriveVolts(0, 0));
}
