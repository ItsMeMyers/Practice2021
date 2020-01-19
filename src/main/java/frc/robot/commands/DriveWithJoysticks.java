package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class DriveWithJoysticks extends CommandBase {

    private double right = 0.0;
    private double left = 0.0;

    public DriveWithJoysticks(Drivetrain drivetrain) {
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        right = OI.rightStick.getRawAxis(Joystick.AxisType.kY.value);
        left = OI.leftStick.getRawAxis(Joystick.AxisType.kY.value);

        if (Math.abs(right) <= .08) {
            right = 0.0;
        }

        if (Math.abs(left) <= .08) {
            left = 0.0;
        }

        OI.drivetrain.setRightPower(right);
        OI.drivetrain.setLeftPower(left);
        OI.drivetrain.drive();
    }

    @Override
    public void end(boolean interrupted) {
        OI.drivetrain.stop();
    }
}