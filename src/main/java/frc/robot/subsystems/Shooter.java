package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
    private static final double degreesOfTurret = 23;
    private static final double diameterOfTurretWheelInches = 4;

    private WPI_TalonFX shootMotor1;
    private WPI_TalonFX shootMotor2;

    private double power = 0.0;

    // TODO Change threshold voltage to a non-placeholder value
    private final double vThreshold = 1.0;

    public Shooter() {

        shootMotor1 = new WPI_TalonFX(Constants.shootMotor1);
        shootMotor2 = new WPI_TalonFX(Constants.shootMotor2);
        shootMotor1.setNeutralMode(NeutralMode.Brake);
        shootMotor2.setNeutralMode(NeutralMode.Brake);
    }

    public void setPower(double pwr) {
        
        if (pwr > 1.0) {
            
            pwr = 1.0;
        } else if (pwr < -1.0) {
            
            pwr = -1.0;
        }

        this.power = pwr;
    }

    public void shoot() {
        shootMotor1.set(power);
    }

    // Calculate required rpm of turret motor for supplied distance (ft)
    public static int DistanceToRPM(double distance){
        int rVal = -1;
        distance = distance / 2;
        double val = distance * 32.6;
        val = val / (Math.sin(2*degreesOfTurret));
        val = Math.sqrt(val);
        rVal = (int)(val / (Math.PI * (diameterOfTurretWheelInches / 12))) * 60;
        return rVal;
    }

    // TODO test this method functionality
    public boolean voltageSpike() {
        
        return (shootMotor1.getBusVoltage() >= vThreshold) || (shootMotor2.getBusVoltage() >= vThreshold);
    }

    public void stopShooter() {
        power = 0.0;
        
        shootMotor1.stopMotor();
        shootMotor2.stopMotor();
    }
}