package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class Turret extends CommandBase {

    private WPI_TalonFX turretMotor;

    public Turret() {
        
        turretMotor = new WPI_TalonFX(Constants.turretMotor);
    }

    
}