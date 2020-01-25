package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveWithJoysticks extends CommandBase {

    private double right = 0.0;
    private double left = 0.0;

    Drivetrain drivetrain;
    Joystick rightStick;
    Joystick leftStick;

    public DriveWithJoysticks(Drivetrain drivetrain, Joystick rightStick, Joystick leftStick) {
        
        this.drivetrain = drivetrain;
        this.rightStick = rightStick;
        this.leftStick = leftStick;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        right = rightStick.getRawAxis(Joystick.AxisType.kY.value);
        left = leftStick.getRawAxis(Joystick.AxisType.kY.value);

        if (Math.abs(right) <= .02) {
            right = 0.0;
        }

        if (Math.abs(left) <= .02) {
            left = 0.0;
        }

        drivetrain.setRightPower(right);
        drivetrain.setLeftPower(left);
        drivetrain.drive();
    }

    // Command will run until interrupted
    @Override
    public boolean isFinished() {
        return false;
    }

    // Runs when isFinished returns true
    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}