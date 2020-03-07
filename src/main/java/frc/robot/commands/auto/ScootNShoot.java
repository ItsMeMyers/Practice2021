package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ScootNShoot extends SequentialCommandGroup {

    public ScootNShoot(Shooter shooter, Drivetrain dt, Double speed, Double shootTime, Double driveTime, Feeder feeder,
            Intake intake) {
        addCommands(
            new SpinUpShooter(shooter),

            new DriveStraight(dt,speed,driveTime, true),
            
            new ShootShooter(shooter, feeder, intake, shootTime)
        );
    }
    
}