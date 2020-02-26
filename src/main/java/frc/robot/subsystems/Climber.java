package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase
{
    private WPI_TalonSRX winchMotor;
    private Solenoid rSolenoid;
    private Solenoid lSolenoid;

    private double climbMotorSpeed = .80;

    public Climber() {
        winchMotor = new WPI_TalonSRX(winchMotorPort);
        rSolenoid = new Solenoid(rSolenoidPort);
        lSolenoid = new Solenoid(lSolenoidPort);
    }

    public void deploy() {
        rSolenoid.set(true);
        lSolenoid.set(true);
    }

    public void climb() {

        winchMotor.set(climbMotorSpeed);
    }

    public void withdraw() {
        rSolenoid.set(false);
        lSolenoid.set(false);
    }

    public void stop() {
        winchMotor.stopMotor();
    }

}