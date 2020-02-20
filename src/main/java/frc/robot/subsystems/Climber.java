package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.Constants.ClimberConstants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase
{
    WPI_TalonSRX motor = new WPI_TalonSRX(ClimberConstants.cMotorPort);
    Solenoid solenoid = new Solenoid(ClimberConstants.cSolenoidPort);
    public void deploy()
    {
        solenoid.set(true);
    }
    public void climb()
    {
    }
    public void undeploy()
    {
        solenoid.set(false);
    }

}