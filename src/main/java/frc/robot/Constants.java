/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.NavxGyro;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

  /* CONSTANTS INFO
   * From the official wpilib docs:
   * "It is recommended that users separate these constants 
   * into individual inner classes corresponding to subsystems or robot modes,
   * to keep variable names shorter."
   * "In Java, all constants should be declared public static final
   * so that they are globally accessible and cannot be changed."
   * "In Java, it is recommended that the constants be used from other classes
   * by statically importing the necessary inner class."
   */

   /**
    * Constants for the drive train
    */
  public static final class DrivetrainConstants {

    public static final double INVERT_MOTOR = -1.0;
    // Drive Train motors
    public static final int R1MotorPort = 0;
    public static final int R2MotorPort = 1;
    public static final int R3MotorPort = 2;
    public static final int L1MotorPort = 3;
    public static final int L2MotorPort = 4;
    public static final int L3MotorPort = 5;

    // Need to edit all the numbers under this for 2020
    public static final int[] leftEncoderPorts = new int[] { 2, 3 };
    public static final int[] rightEncoderPorts = new int[] { 0, 1 };
    public static final boolean leftEncoderReversed = false;
    public static final boolean rightEncoderReversed = false;

    public static final int EncoderCPR = 128;
    public static final double WheelDiameterMeters = 0.1;
    public static final double EncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (WheelDiameterMeters * Math.PI) / (double) EncoderCPR;

    public static final boolean GYRO_REVERSED = true;
  }

  /**
   * Constants for the feeder system
   */
  public static final class FeederConstants {
    public static final double INVERT_MOTOR = -1.0;

    // Ball Present Sensor Config
    public static final int feederBallPresentId = 0;
    public static final double feederBallPresentThreshold = 125.0;

    // Power cell feeder motors
    public static final int motor1Port = 14;
    public static final int motor2Port = 15;
  }

  /**
   * Constants for the intake system
   */
  public static final class IntakeConstants {
    //Ball Present Sensor Config
    public static final int intakeBallPresentId = 1;
    public static final double intakeBallPresentThreshold = 125.0;
    // Power cell intake motor
    public static final int intakeFrontMotorPort = 11;
    public static final int intakeMidMotorPort = 12;
    public static final int intakeLowerTowerPort = 13;
    // Solenoids
    public static final int intakeSolenoidPort = 0;
  }

  /**
   * Constants for the input device ports
   */
  public static final class RobotContainerConstants {
    // Sticks
    public static final int rightStickPort = 0;
    public static final int leftStickPort = 1;
    public static final int gamepadPort = 2;
    // TODO: Add a free button mapping for running the on-the-go pathfinder command

    // Gamepad POV values in degrees
    public static final int povUp = 0;
    public static final int povDown = 180;
  }

  /**
   * Constants for path finding and trajectories
   */
  public static final class RouteFinderConstants {
    // Reasonable baseline values for a RAMSETE follower in units of meters and
    // seconds
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
    // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
    // These characterization values MUST be determined either experimentally or
    // theoretically for *your* robot's drive.
    // The Robot Characterization Toolsuite provides a convenient tool for obtaining
    // these values for your robot.
    public static final double ksVolts = 1.43;
    public static final double kvVoltSecondsPerMeter = 5.15;
    public static final double kaVoltSecondsSquaredPerMeter = 0.384;
    public static final double kTrackwidthMeters = 0.77;

    // Example value only - as above, this must be tuned for your drive!
    public static final double kPDriveVel = -12.5;

    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;

    // On-the-go route finding waypoint variables
    public static final int pointx = 0;
    public static final int pointy = 0;
    public static final int rotation = 0;
    public static final int waypointx = 0;
    public static final int waypointy = 0;
  }

  /**
   * Constants for the shooter subsystem
   */
  public static final class ShooterConstants {
    // Shooter spinner motors
    public static final int kShooterMotorRPort = 6;
    public static final int kShooterMotorLPort = 7;
  }

  /**
   * Constants for the turret subsystem
   */
  public static final class TurretConstants {
    // Shooter spinner and turret moving motors
    public static final int kTurretMotorPort = 8;
    // Turret limit switches
    public static final int kLimitSwitchRPort = 15;
    public static final int kLimitSwitchLPort = 16;
  }
  public static NavxGyro gyro;
  public static final class ClimberConstants
  {
    public static final int cMotorPort = 22;
    public static final int cLSolenoidPort = 55;
    public static final int cRSolenoidPort = 66;
  }
}
