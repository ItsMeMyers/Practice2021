package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeToggle extends CommandBase {

    private Intake intake;

    /**
     * This command changes the mode of the intake system.
     * It changes it from stowed away to out or vice versa.
     * This is different from turning the intake direction from in and out.
     */
    public IntakeToggle(Intake itk) {
        this.intake = itk;
        addRequirements(intake);
    }

    /**
     * When the command is called it toggles the state of the intake system
     */
    @Override
    public void initialize() {
        intake.toggle();
    }
}