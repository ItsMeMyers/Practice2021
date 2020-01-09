package frc.robot;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class OI {

    public static Joystick rightStick = new Joystick(Constants.rightStick);
    public static Joystick leftStick = new Joystick(Constants.leftStick);
    
    public static Drivetrain drivetrain = new Drivetrain();
}