package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class DeployClimber extends CommandBase {

    public Climber climber;
    private final Joystick gamepad;

    public DeployClimber(Climber cl, Joystick gpd) {
        this.climber = cl;
        this.gamepad = gpd;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        // If not deployed already and the user is pressing D pad up
        if (!climber.getDeployed()) {
            climber.deploy();
        }
    }
}