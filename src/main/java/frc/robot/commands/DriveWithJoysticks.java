package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveWithJoysticks extends CommandBase {
    // Represents the position of the right and left joysticks
    private double right = 0.0;
    private double left = 0.0;

    Drivetrain drivetrain;
    Joystick rightStick;
    Joystick leftStick;

    /**
     * This command drives the robot. It receives inputs from the 
     * left and right joysticks and sets the speed of the motors.
     */
    public DriveWithJoysticks(Drivetrain drivetrain, Joystick rightStick, Joystick leftStick) {
        this.drivetrain = drivetrain;
        this.rightStick = rightStick;
        this.leftStick = leftStick;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        // Gets the values of the right and left joystick positions
        right = rightStick.getRawAxis(Joystick.AxisType.kY.value);
        left = leftStick.getRawAxis(Joystick.AxisType.kY.value);

        // If the value of the joystics are too low just set it to zero
        if (Math.abs(right) <= .08) {
            right = 0.0;
        }

        if (Math.abs(left) <= .08) {
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