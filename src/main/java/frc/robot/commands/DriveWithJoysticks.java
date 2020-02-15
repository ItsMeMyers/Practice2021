package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveWithJoysticks extends CommandBase {
    // Threshold within which the motor won't bother moving
    private final double driveThreshold = 0.08;

    // Represents the position of the right and left joysticks
    private double right = 0.0;
    private double left = 0.0;

    Drivetrain drivetrain;
    Joystick rightStick;
    Joystick leftStick;

    /**
     * This command drives the robot. It receives inputs from the 
     * left and right joysticks and sets the speed of the motors.
     * Pushing the left stick forward makes the left wheels go forward.
     * Pushing the right stick forward makes the right wheels go forward.
     * First, if the values of the joysticks are too low, then it sets the speed to 0.
     * Then, it sets the power of the right motors, then the left motors.
     * It takes the value from the sticks and makes sure they are within the interval [-1, 1].
     * Then, it scales the power value by taking the square root of the value.
     * Finally, it drives the robot.
     * This command only stops when it is interrupted.
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

        // If the value of the joysticks are too low just set it to zero
        if (Math.abs(right) <= driveThreshold) {
            right = 0.0;
        }

        if (Math.abs(left) <= driveThreshold) {
            left = 0.0;
        }

        drivetrain.setPower(right, true);
        drivetrain.setPower(left, false);
        drivetrain.drive();
    }

    // Runs when command is interrupted
    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}