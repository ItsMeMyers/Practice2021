package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;

public class ClearAllBalls extends CommandBase {

    private Intake intake;
    private Feeder feeder;
    private Shooter shooter;
    private final XboxController gamepad;

    public ClearAllBalls(Intake itk, Feeder fdr, Shooter str, XboxController gpd) {
        
        this.intake = itk;
        this.feeder = fdr;
        this.shooter = str;
        this.gamepad = gpd
        addRequirements(intake, feeder, shooter);
    }

    @Override
    public void execute() {
        //If user is pressing d pad down
        if (gamepad.getRawButton(RobotContainer.D_Pad_Down)) {
            intake.runAllOut();
            shooter.reverseShooters();
            feeder.reverse();
        }
    }
}