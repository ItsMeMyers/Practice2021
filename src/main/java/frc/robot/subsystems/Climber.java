package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase
{
    private WPI_TalonSRX winchMotor;
    private Solenoid liftSolenoid;

    private boolean deployed = false;

    private double climbMotorSpeedLimiter = 1.0;

    // instanciate motors and solenoids
    public Climber() {
    }

    public void deploy() {
        liftSolenoid.set(true);
        deployed = true;
    }

    // set speed of motor
    public void climb( Double climbSpeed) {
    }

    // retract climber
    public void withdraw() {
    }

    // stop climbing
    public void stop() {
    }

    public boolean getDeployed() {
        return deployed;
    }
}