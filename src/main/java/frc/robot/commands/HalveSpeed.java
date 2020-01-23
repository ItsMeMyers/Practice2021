package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class HalveSpeed extends CommandBase {

    private final Drivetrain drivetrain;

    public HalveSpeed(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    @Override
    public void execute() {
        drivetrain.toggleHalfSpeed();
    }
}
