package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.Constants.ClimberConstants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase
{
    WPI_TalonSRX motor;
    Solenoid solenoid;
    public Climber()
    {
         motor = new WPI_TalonSRX(ClimberConstants.cMotorPort);
         solenoid = new Solenoid(ClimberConstants.cSolenoidPort);
    }

    /**Deploy the climbing system */
    public void deploy()
    {
        solenoid.set(true);
    }
    /** Actually climb when the button/trigger is held */
    public void climb(double value)
    {
        motor.set(value);
    }
    /**Stop climbing*/
    public void undeploy()
    {
        motor.set(0.0);
        solenoid.set(false);
    }

}