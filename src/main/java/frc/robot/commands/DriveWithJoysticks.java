package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class DriveWithJoysticks extends CommandBase {

    private double right = 0.0;
    private double left = 0.0;

    private final Drivetrain drivetrain;

    public DriveWithJoysticks(Drivetrain drivetrain) {
        addRequirements(drivetrain);
        this.drivetrain = drivetrain;
    }

    @Override
    public void execute() {
        right = RobotContainer.rightStick.getRawAxis(Joystick.AxisType.kY.value);
        left = RobotContainer.leftStick.getRawAxis(Joystick.AxisType.kY.value);

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

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}