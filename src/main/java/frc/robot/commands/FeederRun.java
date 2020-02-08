package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class FeederRun extends CommandBase {
    
    Feeder feeder;

    public FeederRun(Feeder fd) {
        this.feeder = fd;
        addRequirements(feeder);
    }

    @Override
    public void execute() {
        feeder.run();
    }

}