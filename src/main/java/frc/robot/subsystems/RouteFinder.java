package frc.robot.subsystems;

import java.util.List;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;

public class RouteFinder extends SubsystemBase {

    private Command pathCommand;

    public RouteFinder() {
        // Dummy values for now
        int pointx = 0;
        int pointy = 0;
        int rotation = 0;
        // An example trajectory to follow.  All units in meters.
        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(rotation)),
            // Pass through these waypoints
            List.of(
                new Translation2d(2, 0)
            ),
            // End at this location
            new Pose2d(pointx, pointy, new Rotation2d(rotation)),
            // Pass config
            RobotContainer.config
        );

        pathCommand = Robot.m_robotContainer.getAutonomousCommand(trajectory);
    }

    /** Grants access to the pathCommand in RouteFinder */
    public Command getPathCommand()
    {
        return pathCommand;
    }
}