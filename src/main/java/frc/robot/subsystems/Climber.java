package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.Constants.ClimberConstants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase
{
    private WPI_TalonSRX winchMotor;
    private Solenoid rSolenoid;
    private Solenoid lSolenoid;

    private double climbMotorSpeed = .80;

    public Climber() {
        winchMotor = new WPI_TalonSRX(ClimberConstants.cMotorPort);
        rSolenoid = new Solenoid(ClimberConstants.cRSolenoidPort);
        lSolenoid = new Solenoid(ClimberConstants.cLSolenoidPort);
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
        winchMotor.set(0.0);
    }

}