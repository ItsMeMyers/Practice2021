package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DataRecorder;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase {

    Shooter shooter;
    Feeder feeder;
    XboxController gamepad;
    Limelight limelight;
    DataRecorder dataRecorder;

    private double dist = 0.0;

    private final double targetHeight = 98.25;
    private final double mountHeight = 36.0;
    private final double angleToGround = (Math.PI / 6.0);
    private double angleToTarget = 0.0;

    private boolean hasValidTarget = false;
    private double ty;
    private double tx;

    public RunShooter(Shooter sht, Feeder fd, Limelight ll, DataRecorder dR) {
        this.shooter = sht;
        this.limelight = ll;
        this.dataRecorder = dR;
        this.feeder = fd;

        addRequirements(shooter);
    }

    @Override
    public void execute() {

        updateLimelightTracking();
        dist = findDistance();
        
        while (!shooter.atSpeed(shooter.getShooterRPMs())) {
            shooter.shoot();
        }
        while (!shooter.atSpeed(shooter.getShooterRPMs())) {
            new FeederRun(feeder);
        }
        feeder.shotBall();
        
        /*
        if (shooter.voltageSpike()) {
            dataRecorder.setDistance(dist);
            dataRecorder.setX(limelight.x());
            dataRecorder.setY(limelight.y());
        } */
    }

    // Command stops when interrupted
    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            shooter.stopShooter();
        }
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
        
        tx = limelight.x();
        ty = limelight.y();
    }
}