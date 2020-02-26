package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
    private final Climber climber;
    private double pwr;

    public Climb(Climber cb)
    {
        climber = cb;
        addRequirements(climber);
    }

    

    @Override
    public void initialize()
    {
        climber.deploy();
    }
    @Override
    public void execute() {
        climber.climb(pwr);
    }


    
    @Override
    public void end(boolean interrupted)
    {
        climber.undeploy();
    }
}