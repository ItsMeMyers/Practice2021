package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DataRecorder;
import frc.robot.subsystems.Turret;

public class RunShooter extends CommandBase {

    Turret turret;
    XboxController gamepad;
    DataRecorder dataRecorder;

    DigitalInput indicatorSwitch;

    private double power = 0.0;
    private double dist = 0.0;

    public RunShooter(Turret trrt, XboxController gmpd, DigitalInput idSw, DataRecorder dR) {
        this.turret = trrt;
        this.gamepad = gmpd;
        this.dataRecorder = dR;
        this.indicatorSwitch = idSw;
        addRequirements(turret);
    }

    @Override
    public void execute() {

        power = gamepad.getTriggerAxis(Hand.kRight);

        // TODO grab limelight distance data
        // We have the LimeLight filtering out everything but the reflective target
        // LimeLight can't properly detect the target at all distances
        // We're going to use OpenCV to detect the shape, it's going to be a bit before we have working code.
        dist = 0.0;

        if (Math.abs(power) <= .02) {
            power = 0.0;
        }

        turret.setSpinPower(power);
        turret.shoot();

        if (indicatorSwitch.get()) {
            dataRecorder.setSpeed(power);
            dataRecorder.setDistance(dist);
        }
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