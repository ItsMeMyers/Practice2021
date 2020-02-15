/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

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
  public static final double INVERT_MOTOR = -1.0;

  // An example trajectory to follow.  All units in meters.
  /*
  * rotation = 0
  * x = 5
  y = 0

  */
  /** Creates a trajectory using the three values(pointx, pointy, rotation)
   *  and also a list of points to pass through(List<Translation2d list). */
  public static Trajectory trajectorygen(int pointx, int pointy, int rotation, List<Translation2d> list){
    return TrajectoryGenerator.generateTrajectory(
    // Start at the origin facing the +X direction
    new Pose2d(0, 0, new Rotation2d(rotation)),
    // Pass through these waypoints
    list,
    // End at this location
    new Pose2d(pointx, pointy, new Rotation2d(rotation)),
    // Pass config
    RobotContainer.config
    );
  }

  // Drive Train motors
  public static final int r1Motor = 0;
  public static final int r2Motor = 1;
  public static final int r3Motor = 2;
  public static final int l1Motor = 3;
  public static final int l2Motor = 4;
  public static final int l3Motor = 5;

  // Shooter spinner and turret moving motors
  public static final int shootMotorR = 6;
  public static final int shootMotorL = 7;
  public static final int turretMotor = 8;

  // Climber motors
  public static final int cMotor1 = 9;
  public static final int cMotor2 = 10;

  // Power cell intake and feeder motors
  public static final int intakeMotor = 11;
  public static final int indexerMotor = 12;
  public static final int lTowerMotor = 13;
  public static final int feederMotor1 = 14;
  public static final int feederMotor2 = 15;

  public static final int rightStick = 0;
  public static final int leftStick = 1;
  public static final int gamepad = 2;

  // Solenoids
  public static final int panelSolenoid = 0;

  // Turret limit switches
  public static final int limitSwitchR = 15;
  public static final int limitSwitchL = 16;

  /**
   * 
   * Need to edit all the numbers under this for 2020
   * 
   **/

  public static final int[] kLeftEncoderPorts = new int[] { 2, 3 };
  public static final int[] kRightEncoderPorts = new int[] { 0, 1 };
  public static final boolean kLeftEncoderReversed = false;
  public static final boolean kRightEncoderReversed = false;

  public static final double kTrackwidthMeters = 0.77;
  public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

  public static final int kEncoderCPR = 128;
  public static final double kWheelDiameterMeters = 0.1;
  public static final double kEncoderDistancePerPulse =
      // Assumes the encoders are directly mounted on the wheel shafts
      (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;

  public static final boolean kGyroReversed = true;

  // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
  // These characterization values MUST be determined either experimentally or
  // theoretically
  // for *your* robot's drive.
  // The Robot Characterization Toolsuite provides a convenient tool for obtaining
  // these
  // values for your robot.
  public static final double ksVolts = 1.43;
  public static final double kvVoltSecondsPerMeter = 5.15;
  public static final double kaVoltSecondsSquaredPerMeter = 0.384;

  // Example value only - as above, this must be tuned for your drive!
  public static final double kPDriveVel = -12.5;

  public static final int kDriverControllerPort = 1;

  public static final double kMaxSpeedMetersPerSecond = 3;
  public static final double kMaxAccelerationMetersPerSecondSquared = 3;

  // Reasonable baseline values for a RAMSETE follower in units of meters and
  // seconds
  public static final double kRamseteB = 2;
  public static final double kRamseteZeta = 0.7;

  public static final int pov = 0;
  public static final int povUp = 0;
  public static final int povDown = 180;
}
