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
        Trajectory trajectory = Constants.trajectorygen(pointx, pointy, rotation,List.of(new Translation2d(2,0)));
        pathCommand = Robot.m_robotContainer.getAutonomousCommand(trajectory);
    }

    /** Grants access to the pathCommand in RouteFinder */
    public Command getPathCommand()
    {
        return pathCommand;
    }
}