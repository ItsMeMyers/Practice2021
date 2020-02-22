package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class DeployClimber extends CommandBase {

    public Climber climber;

    public DeployClimber(Climber cl) {

        this.climber = cl;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        climber.deploy();
    }
}