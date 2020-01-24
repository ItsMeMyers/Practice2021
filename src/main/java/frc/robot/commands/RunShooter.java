package frc.robot.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DataRecorder;
import frc.robot.subsystems.Turret;

public class RunShooter extends CommandBase {

    Turret turret;
    XboxController gamepad;
    DataRecorder dataRecorder;

    private double power = 0.0;
    private double distance = 0.0;

    public RunShooter(Turret trrt, XboxController gmpd, DataRecorder dR) {
        this.turret = trrt;
        this.gamepad = gmpd;
        this.dataRecorder = dR;
        addRequirements(turret);
    }

    @Override
    public void execute() {
        
        power = gamepad.getY(Hand.kRight);
        
        // TODO grab limelight distance data
        distance = 0.0;

        if (Math.abs(power) <= .02) {
            power = 0.0;
        }

        turret.setSpinPower(power);
        turret.shoot();
        dataRecorder.setSpeed(power);
        dataRecorder.setDistance(distance);
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