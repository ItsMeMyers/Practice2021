package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
public class WithdrawClimber extends CommandBase {

    private Climber climber;
    private final XboxController gamepad;

    public WithdrawClimber(Climber cl, XboxController gpd) {
        
        this.climber = cl;
        this.gamepad = gpd;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        //Not assigned to any button as of right now
        if (false) {
            climber.withdraw();
        }
    }
}