package frc.robot.subsystems;

import static frc.robot.Constants.RouteFinderConstants.*;

import java.util.List;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RouteFinder extends SubsystemBase {

        private static Drivetrain drivetrain;

        public RouteFinder(Drivetrain dt) {
                RouteFinder.drivetrain = dt;
        }
    /** Grants access to the pathCommand in RouteFinder */
    // TODO: Arnav what the fuck is this please change it or put in some explanations
    // Ok so basically this returns a command that makes the robot go to a specified point
    // and end up at the specified rotation. It also has to pass through the desired waypoint.
    public static Command getPathCommand(int pointx, int pointy, int rotation, int waypointx, int waypointy) {
        // The path to take
        Trajectory foundTrajectory = trajectorygen(pointx, pointy, rotation,
                List.of(new Translation2d(waypointx, waypointy)));

        RamseteCommand ramseteCommand = new RamseteCommand(foundTrajectory, // Our desired trajectory
                drivetrain::getPose,
                new RamseteController(kRamseteB, kRamseteZeta),
                new SimpleMotorFeedforward(ksVolts,
                        kvVoltSecondsPerMeter,
                        kaVoltSecondsSquaredPerMeter),
                new DifferentialDriveKinematics(kTrackwidthMeters),
                drivetrain::getWheelSpeeds,
                new PIDController(kPDriveVel, 0, 0),
                new PIDController(kPDriveVel, 0, 0),
                // RamseteCommand passes volts to the callback
                drivetrain::tankDriveVolts,
                drivetrain);
        
        // Follow the path finding command and then stop
        return ramseteCommand.andThen(() -> drivetrain.tankDriveVolts(0, 0));
    }

    /**
     * Creates a trajectory using the three values(pointx, pointy, rotation) Let me
     * explain: pointx and pointy are the x and y coordinates that you want to end
     * at. rotation is the rotation that you want to end at. and also a list of
     * points to pass through(List<Translation2d list).
     */
    public static Trajectory trajectorygen(int pointx, int pointy, int rotation, List<Translation2d> list) {
        var kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);
        // Create a voltage constraint to ensure we don't accelerate too fast
        var autoVoltageConstraint =
            new DifferentialDriveVoltageConstraint(
                new SimpleMotorFeedforward(ksVolts,
                                           kvVoltSecondsPerMeter,
                                           kaVoltSecondsSquaredPerMeter),
                kDriveKinematics,
                10);
            // Create config for trajectory
    TrajectoryConfig config =
    new TrajectoryConfig(kMaxSpeedMetersPerSecond,
                         kMaxAccelerationMetersPerSecondSquared)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(kDriveKinematics)
        // Apply the voltage constraint
        .addConstraint(autoVoltageConstraint)
        //Doesn't reverse the trajectory
        .setReversed(false);

        return TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(rotation)),
                // Pass through these waypoints
                list,
                // End at this location
                new Pose2d(pointx, pointy, new Rotation2d(rotation)),
                // Pass config
                config);
    }
}