package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.Constants;

public class DeployClimber extends CommandBase {

    public Climber climber;
    private final XboxController gamepad;

    public DeployClimber(Climber cl, XboxController gpd) {
        this.climber = cl;
        this.gamepad = gpd;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        //If not deployed already and the user is pressing D pad up
        if (!climber.getDeployed() && gamepad.getRawButton(Constants.D_Pad_Up)) {
            climber.deploy();
        }
    }
}