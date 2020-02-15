package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeIn extends CommandBase {

    Intake intake;

    /**
     * This takes in a ball from the ground.
     * Balls from the intake are then stored in the feeder.
     * This changes the intake direction, not whether it is stowed in or not.
     */
    public IntakeIn(Intake itk) {
        this.intake = itk;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.runIn();
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            intake.stopMotor();
        }
    }
}