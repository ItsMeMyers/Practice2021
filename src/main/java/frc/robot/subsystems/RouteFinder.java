package frc.robot.subsystems;

import static frc.robot.Constants.RouteFinderConstants.*;

import java.util.List;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class RouteFinder extends SubsystemBase {

    /** Grants access to the pathCommand in RouteFinder */
    // TODO: Arnav what the fuck is this please change it or put in some explanations
    public static Command getPathCommand(int pointx, int pointy, int rotation, int waypointx, int waypointy) {
        Trajectory foundTrajectory = trajectorygen(pointx, pointy, rotation,
                List.of(new Translation2d(waypointx, waypointy)));
        RamseteCommand ramseteCommand = new RamseteCommand(foundTrajectory, // We input our desired trajectory
                                                                            // here
                RobotContainer.drivetrain::getPose, new RamseteController(kRamseteB, kRamseteZeta),
                new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter,
                        kaVoltSecondsSquaredPerMeter),
                kDriveKinematics, RobotContainer.drivetrain::getWheelSpeeds,
                new PIDController(kPDriveVel, 0, 0), new PIDController(kPDriveVel, 0, 0),
                // RamseteCommand passes volts to the callback
                RobotContainer.drivetrain::tankDriveVolts, RobotContainer.drivetrain);
        return ramseteCommand.andThen(() -> RobotContainer.drivetrain.tankDriveVolts(0, 0));
    }

    /**
     * Creates a trajectory using the three values(pointx, pointy, rotation) Let me
     * explain: pointx and pointy are the x and y coordinates that you want to end
     * at. rotation is the rotation that you want to end at. and also a list of
     * points to pass through(List<Translation2d list).
     */
    public static Trajectory trajectorygen(int pointx, int pointy, int rotation, List<Translation2d> list) {
        return TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(rotation)),
                // Pass through these waypoints
                list,
                // End at this location
                new Pose2d(pointx, pointy, new Rotation2d(rotation)),
                // Pass config
                RobotContainer.config);
    }
}