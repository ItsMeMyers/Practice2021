package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class MoveTurret extends CommandBase {
    
    // If the speed of the motor is less than this threshold, we'll just set it to zero
    private final double powerThreshold = 0.02;
    private double power = 0.0;

    private Turret turret;
    private XboxController gamepad;

    // TODO: This command doesn't seem to be implemented anywhere
    public MoveTurret(Turret trrt, XboxController gmpd) {
        this.turret = trrt;
        this.gamepad = gmpd;
        addRequirements(turret);
    }

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

    // Runs when command is interrupted
    @Override
    public void end(boolean interrupted) {
        turret.stopTurret();
    }
}