package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class WithdrawClimber extends CommandBase {

    private Climber climber;

    public WithdrawClimber(Climber cl) {
        
        this.climber = cl;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        
        climber.withdraw();
    }
}