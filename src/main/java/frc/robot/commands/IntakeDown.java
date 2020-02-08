package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeDown extends CommandBase {

    Intake intake;

    public IntakeDown(Intake itk) {
        this.intake = itk;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.down();
    }
}