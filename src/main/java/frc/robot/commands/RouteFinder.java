package frc.robot.commands;

import java.util.List;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class RouteFinder extends SubsystemBase {

    private static Drivetrain drivetrain;

    public RouteFinder(Drivetrain dt) {
        RouteFinder.drivetrain = dt;
    }

    /**
     * Creates a command to follow a certain trajectory.
     * 
     * This command will be repeatedly called in Robot.java. It constantly calculates
     * a new command to go to the position. The command keeps getting calculates
     * 
     * @param trajectory the trajectory to follow
     * @return the command that satisifes the aforementioned conditions
     */
    public static Command getPathCommand(Trajectory trajectory) {

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
        return null;
        // RamseteCommand ramseteCommand = new RamseteCommand(trajectory, // Our desired trajectory
        //         drivetrain::getPose, new RamseteController(kRamseteB, kRamseteZeta),
        //         new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter),
        //         new DifferentialDriveKinematics(kTrackwidthMeters), drivetrain::getWheelSpeeds,
        //         new PIDController(kPDriveVel, 0, 0), new PIDController(kPDriveVel, 0, 0),
        //         // RamseteCommand passes volts to the callback
        //         drivetrain::tankDriveVolts,
        //         // requires the drivetrain, will be interrupted if another command requires it
        //         drivetrain);

        // // Follow the path finding command and then stop
        // return ramseteCommand.andThen(() -> drivetrain.tankDriveVolts(0, 0));
    }

    /**
     * This generates a trajectory that starts at the current pose with a few features:
     * 
     * @param pointx   the x-coordinate to end up at
     * @param pointy   the y-coordinate to end up at
     * @param rotation the rotation to end up at
     * @return the trajectory that satisfies the aforementioned conditions
     */
    public static Trajectory trajectorygen(int pointx, int pointy, int rotation) {
        return TrajectoryGenerator.generateTrajectory(
                // Start at the current pose
                drivetrain.getPose(),
                // Pass through these waypoints
                List.of(),
                // End at this location
                new Pose2d(pointx, pointy, new Rotation2d(rotation)),
                // Pass config
                RobotContainer.getConfig());
    }
}