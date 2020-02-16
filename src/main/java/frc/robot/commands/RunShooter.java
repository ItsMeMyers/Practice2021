package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DataRecorder;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase {

    private Shooter shooter;
    private Feeder feeder;
    private Limelight limelight;
    private DataRecorder dataRecorder;

    private double dist = 0.0;

    // How high the outer port is above the ground (inches)
    private final double targetHeight = 98.25;
    // How high the shooter is above the ground (inches)
    private final double mountHeight = 36.0;
    // in radians, equivalent to 30 degrees
    private final double angleToGround = (Math.PI / 6.0);
    private double angleToTarget = 0.0;

    private boolean hasValidTarget = false;
    private double ty;
    private double tx;

    public RunShooter(Shooter sht, Feeder fd, Limelight ll, DataRecorder dR) {
        this.shooter = sht;
        this.feeder = fd;
        this.limelight = ll;
        this.dataRecorder = dR;
        addRequirements(shooter, feeder);
    }

    @Override
    public void execute() {

        updateLimelightTracking();
        dist = findDistance();
        
        while (!shooter.atSpeed()) {
            shooter.getToSpeed();
        }
        while (shooter.atSpeed()) {
            // TODO: What does this even do it doesn't schedule it or anything it just initializes it
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

    /**
     * Command stops when interrupted
     * */
    @Override
    public void end(boolean interrupted) {
        shooter.stopShooter();
    }

    /**
     * @return the distance to the target
     */
    public double findDistance() {
        angleToTarget = ty; // Get from limelight
        return ((targetHeight - mountHeight) / (double) (Math.tan(angleToGround + angleToTarget)));
    }

    /**
     * Checks if the limelight has a target.
     * If it does, update the tx and ty values.
     */
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