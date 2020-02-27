package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;

public class SpinColorWheel extends CommandBase {

    private ColorWheel colorWheel;
    private final XboxController gamepad;

    public SpinColorWheel(ColorWheel clw) {
        
        this.colorWheel = clw;
        addRequirements(colorWheel);
    }

    @Override
    public void execute() {
        //Nothing yet
        if (false) {
            colorWheel.spin();
        }
    }

    @Override
    public void end(boolean interrupted) {
        colorWheel.stop();
    }
}