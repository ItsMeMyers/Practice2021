package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeIn extends CommandBase {

    private Intake intake;

    /**
     * 1. Changes the intake direction to take in balls from the ground. <br>
     * 2. Hold the right trigger to activate the command. <br>
     * 3. Balls from the intake are then stored in the feeder. <br>
     * 4. This changes the intake direction, not whether it is stowed in or not.
     */
    public IntakeIn(Intake itk) {
        this.intake = itk;
        addRequirements(intake);
    }

    /**
     * Spins the motor so balls are taken in
     */
    @Override
    public void initialize() {
        intake.runIn();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            intake.stopMotor();
        }
    }
}