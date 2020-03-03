package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveStraight extends CommandBase {

    private Drivetrain drivetrain;

    private double speed = 1.0;
    private double currtime = 0.0;
    private double startTime = 0.0;
    private double dur = 0.0;
    private Timer t = new Timer();

    public DriveStraight(Drivetrain dt,Double driveSpeed, Double driveTime) {
        
        this.drivetrain = dt;
        this.speed = driveSpeed;
        this.dur = driveTime;
        startTime = t.getFPGATimestamp();
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        // Set percentage drive speed
        drivetrain.setRightPower(speed);
        drivetrain.setLeftPower(speed);
        drivetrain.drive();
    }

    @Override
    public boolean isFinished() {
        currtime = t.getFPGATimestamp();
        if(currtime - startTime >= dur){
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}