package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class MoveTurret extends CommandBase {
    
    // If the speed of the motor is less than this threshold, we'll just set it to zero
    private final double powerThreshold = 0.02;
    private double power = 0.0;

    private final Turret turret;
    private final XboxController gamepad;

    // TODO: This command doesn't seem to be implemented anywhere
    public MoveTurret(Turret trrt, XboxController gmpd) {
        this.turret = trrt;
        this.gamepad = gmpd;
        addRequirements(turret);
    }

    /**
     * 1. This command moves the turret. <br>
     * 2. First it checks if it is next to the limit switches. <br>
     * 3. If it is, it sets the power to 0 so the turret cannot move past the limit. <br>
     * 4. If it isn't, the turret takes its power value from the left joystick on the game pad. <br>
     * 5. Then, it makes sure that the power given is not less than the power threshold of 0.02.
     * If it is, then it just sets the power to 0. <br>
     * 6. Finally, it sends the speed value to the motor.
     */
    @Override
    public void execute() {
        // If the limit switch is not activated then set the power
        // to the left joystick on the gamepad
        if (!(turret.getLimitR() || turret.getLimitL())) {
            power = gamepad.getX(Hand.kLeft);
        } else {
            power = 0.0;
        }

        // If the power is minimal just set it to 0
        if (Math.abs(power) <= powerThreshold) {
            power = 0.0;
        }

        turret.setSpinPower(power);
    }

    /**
     * Stops the turret when the command ends
     */
    @Override
    public void end(boolean interrupted) {
        turret.stopTurret();
    }
}