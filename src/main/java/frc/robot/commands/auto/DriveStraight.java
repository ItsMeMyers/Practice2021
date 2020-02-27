package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveStraight extends CommandBase {

    private Drivetrain drivetrain;

    private double power = 1.0;

    public DriveStraight(Drivetrain dt) {
        
        this.drivetrain = dt;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        
        drivetrain.setRightPower(power);
        drivetrain.setLeftPower(power);
        drivetrain.drive();
    }

    @Override
    public void end(boolean interrupted) {

        if (interrupted) {
            drivetrain.stop();
        }
    }
}