package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class PancakeController extends CommandBase {

    public Shooter shooter;
    private final XboxController gamepad;

    public PancakeController(Shooter str, XboxController gpd) {
        this.shooter = str;
        this.gamepad = gpd;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        //As of right now... its either open all the way or collapsed... subject to change

        //If use says to expand and we are not expanded
        // if (gamepad.getRawButton(Constants.D_Pad_Right) && !shooter.getPancakeExpanded()) {
        //     shooter.setPancake(true);
        // //If user says to collape and we are not collapsed
        // } else if (gamepad.getRawButton(Constants.D_Pad_Left) && shooter.getPancakeExpanded()) {
        //     shooter.setPancake(false);
        // }
    }
}