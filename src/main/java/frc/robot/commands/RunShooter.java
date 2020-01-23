package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class RunShooter extends CommandBase {

    Turret turret;
    XboxController gamepad;

    private double power = 0.0;

    public RunShooter(Turret trrt, XboxController gmpd) {
        this.turret = trrt;
        this.gamepad = gmpd;
        addRequirements(turret);
    }

    @Override
    public void execute() {
        
        power = gamepad.getY(Hand.kRight);

        if (Math.abs(power) <= .02) {
            power = 0.0;
        }

        turret.setSpinPower(power);
        turret.shoot();
    }

    // Command runs until interrupted
    @Override
    public boolean isFinished() {
        
        return false;
    }

    // Command stops when interrupted
    @Override
    public void end(boolean interrupted) {
        
        turret.stopShooter();
    }
}