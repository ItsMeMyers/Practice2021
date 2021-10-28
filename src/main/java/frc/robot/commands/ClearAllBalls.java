package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ClearAllBalls extends CommandBase {

    private Intake intake;
    private Feeder feeder;
    private Shooter shooter;
    private final Joystick gamepad;

    public ClearAllBalls(Intake itk, Feeder fdr, Shooter str, Joystick gpd) {
        
        this.intake = itk;
        this.feeder = fdr;
        this.shooter = str;
        this.gamepad = gpd;
        addRequirements(intake, feeder, shooter);
    }

    @Override
    public void execute() {
        //If user is pressing d pad down
        
    }
}