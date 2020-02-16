package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeOut extends CommandBase {

    private Intake intake;

    /**
     * 1. Changes the intake direction to take in balls from the ground. <br>
     * 2. Hold the right button to activate the command. <br>
     * 3. Balls can be pushed out onto the ground. <br>
     * 4. This changes the intake direction, not whether it is stowed out or not.
     */
    public IntakeOut(Intake itk) {
        this.intake = itk;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.runOut();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            intake.stopMotor();
        }
    }
}