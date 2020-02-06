package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DataRecorder;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class RunShooter extends CommandBase {

    Turret turret;
    XboxController gamepad;
    Limelight limelight;
    DataRecorder dataRecorder;

    private double power = 0.0;
    private double dist = 0.0;

    private final double targetHeight = 98.25;
    private final double mountHeight = 36.0;
    private final double angleToGround = (Math.PI / 6.0);
    private double angleToTarget = 0.0;

    private boolean hasValidTarget = false;
    private double ty;

    public RunShooter(Turret trrt, XboxController gmpd, Limelight ll, DataRecorder dR) {
        this.turret = trrt;
        this.gamepad = gmpd;
        this.limelight = ll;
        this.dataRecorder = dR;

        addRequirements(turret);
    }

    @Override
    public void execute() {

        power = gamepad.getTriggerAxis(Hand.kRight);

        updateLimelightTracking();
        dist = findDistance();

        if (Math.abs(power) <= .02) {
            power = 0.0;
        }

        turret.setSpinPower(power);
        turret.shoot();

        if (turret.voltageSpike()) {
            dataRecorder.setSpeed(power);
            dataRecorder.setDistance(dist);
            dataRecorder.setX(limelight.x());
            dataRecorder.setY(limelight.y());
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

    public double findDistance() {

        // d = (targetHeight - mountHeight) / (tan(angleToGround+angleToTarget))
        angleToTarget = ty;
        return ((targetHeight - mountHeight) / (double)(Math.tan(angleToGround+angleToTarget)));
    }

    public void updateLimelightTracking() {

        hasValidTarget = limelight.hasTargets();
        if (!hasValidTarget) {
            dist = 0.0;
            return;
        }
        
        ty = limelight.y();
    }
}