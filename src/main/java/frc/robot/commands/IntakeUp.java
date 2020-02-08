package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeUp extends CommandBase {

    Intake intake;

    public IntakeUp(Intake itk) {
        this.intake = itk;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.up();
    }  
}