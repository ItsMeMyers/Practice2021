package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.XboxController;

public class IntakeToggle extends CommandBase {

    private Intake intake;
    private final XboxController gamepad;

    /**
     * 1. This command stows in the intake system and puts it out.
     * 2. Press the A button to toggle it.
     * 3. It will toggle it from stowed in to out or vice versa.
     * 4. This is different from turning the intake direction from in and out.
     */
    public IntakeToggle(Intake itk, XboxController gpd) {
        this.intake = itk;
        this.gamepad = gpd;
        addRequirements(intake);
    }

    /**
     * When the command is called it toggles the state of the intake system
     */
    @Override
    public void initialize() {
        //intake.toggle();

        //If user commands to engage, and we aren't already
        if (gamepad.getRawButton(RobotContainer.X_Button)) {
            if (!intake.getEngaged()) {
                intake.forceTo(true);
            }
        // If user commands to disengage and we are currently engaged
        } else if (gamepad.getRawButton(RobotContainer.Y_Button)) {
            if (intake.getEngaged()) {
                intake.forceTo(false);
            }
        }
    }
}