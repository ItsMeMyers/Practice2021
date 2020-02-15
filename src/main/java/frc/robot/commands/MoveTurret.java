package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class MoveTurret extends CommandBase {
    private double power = 0.0;

    Turret turret;
    XboxController gamepad;

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
        if (Math.abs(power) <= .02) {
            power = 0.0;
        }

        turret.setSpinPower(power);
        turret.moveTurret();
    }

    // Runs when command is interrupted
    @Override
    public void end(boolean interrupted) {
        turret.stopTurret();
    }
}