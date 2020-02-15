package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
    private static final double degreesOfTurret = 23;
    private static final double diameterOfTurretWheelInches = 4;

    private WPI_TalonFX shootMotorR;
    private WPI_TalonFX shootMotorL;

    private double rightRatio = 5.0;
    private double leftRatio = 3.0;
    private double encoderEPR = 4096.0;

    private double[] RPMs = new double[2];

    private double rpmThreshold = 0.0;

    private final double vThreshold = 1.0;

    public Shooter() {

        shootMotorR = new WPI_TalonFX(Constants.shootMotorR);
        shootMotorL = new WPI_TalonFX(Constants.shootMotorL);
        
        shootMotorR.setNeutralMode(NeutralMode.Coast);
        shootMotorL.setNeutralMode(NeutralMode.Coast);

        shootMotorL.follow(shootMotorR);
        // shootMotorL.setInverted(true);
        shootMotorL.setInverted(InvertType.OpposeMaster);
    }

    public void geToSpeed() {
        shootMotorR.set(1.0);
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

    public double[] getShooterRPMs() {
        RPMs[0] = shootMotorR.getSelectedSensorVelocity() * 600.0 / (rightRatio * encoderEPR);
        RPMs[1] = shootMotorL.getSelectedSensorVelocity() * 600.0 / (leftRatio * encoderEPR);
        return RPMs;
    }

    public boolean atSpeed(double[] rpms) {
        return (rpms[0] >= rpmThreshold && rpms[1] >= rpmThreshold);
    }

    // TODO test this method functionality
    public boolean voltageSpike() {
        
        return (shootMotorR.getBusVoltage() >= vThreshold) || (shootMotorL.getBusVoltage() >= vThreshold);
    }

    public void stopShooter() {
        
        shootMotorR.stopMotor();
        shootMotorL.stopMotor();
    }
}