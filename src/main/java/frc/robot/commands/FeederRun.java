package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class FeederRun extends CommandBase {
    
    Feeder feeder;

    /**
     * This feeds the balls to the shooter mechanism.
     * It runs the feeder system that brings the balls up to be shot.
     */
    public FeederRun(Feeder fd) {
        this.feeder = fd;
        addRequirements(feeder);
    }

    @Override
    public void execute() {
        feeder.run();
    }
}