package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.auto.DriveStraight;
import frc.robot.subsystems.*;

public class SimpleAuto2 extends SequentialCommandGroup {

    public SimpleAuto2(Shooter shooter, Turret turret, Drivetrain dt, Double speed, Double shootTime, Double driveTime, Feeder feeder, Intake intake){
        addCommands(
            new SpinUpShooter(shooter),
            
            new ShootShooter(shooter, feeder, intake, shootTime),

            new DriveStraight(dt,speed,driveTime),
            
            new StopShooter(shooter)
        );
    }
    
}