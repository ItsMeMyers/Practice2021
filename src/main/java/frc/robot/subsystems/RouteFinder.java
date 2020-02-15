package frc.robot.subsystems;

import java.util.List;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import frc.robot.Robot;
import java.util.List;
public class RouteFinder extends SubsystemBase {

    private Command pathCommand;
      
    public RouteFinder(int pointx, int pointy, int rotation) {
        // An example trajectory to follow.  All units in meters.
        //Also, I might add a list parameter instead.
        Trajectory trajectory = trajectorygen(pointx, pointy, rotation,List.of(new Translation2d(2,0)));
        pathCommand = Robot.m_robotContainer.getAutonomousCommand();
    }

    /** Grants access to the pathCommand in RouteFinder */
    public Command getPathCommand()
    {
        return pathCommand;
    }
/** Creates a trajectory using the three values(pointx, pointy, rotation)
   * Let me explain:
   *    pointx and pointy are the x and y coordinates that you want to end at.
   *    rotation is the rotation that you want to end at.
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
}