package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    
    private static final double degreesOfTurret = 23;
    private static final double diameterOfTurretWheelInches = 4;

    private WPI_TalonFX shootMotorR;
    private WPI_TalonFX shootMotorL;

    private double rightRatio = 5.0;
    private double leftRatio = 3.0;
    private double encoderEPR = 4096.0;

    private double rightRPM;
    private double leftRPM;

    private double rpmThreshold = 0.0;

    private final double vThreshold = 1.0;

    /**
     * Shoots the balls into the targets.
     */
    public Shooter() {

        shootMotorR = new WPI_TalonFX(kShooterMotorRPort);
        shootMotorL = new WPI_TalonFX(kShooterMotorLPort);
        
        // When the motors are in neutral mode the motors will keep moving easily (coast)
        shootMotorR.setNeutralMode(NeutralMode.Coast);
        shootMotorL.setNeutralMode(NeutralMode.Coast);

        // This means the left motor speed will be equal to the right motor speed
        shootMotorL.follow(shootMotorR);
        // The left motor needs to spin the opposite direction of the right motor
        shootMotorL.setInverted(InvertType.OpposeMaster);
    }

    /**
     * Starts the shooter motors
     */
    public void getToSpeed() {
        shootMotorR.set(1.0);
    }

    /**
     * @param distance in ft
     * @return Required rpm of turret motor to shoot that far
     */
    // TODO: Explain this
    public static int DistanceToRPM(double distance){
        distance = distance / 2;
        double val = distance * 32.6;
        val = val / (Math.sin(2 * degreesOfTurret));
        val = Math.sqrt(val);
        return (int)(val / (Math.PI * (diameterOfTurretWheelInches / 12))) * 60;
    }

    /**
     * @return Updates the RPM value of the right shooter motor
     */
    public double getRightRPM() {
        rightRPM = shootMotorR.getSelectedSensorVelocity() * 600.0 / (rightRatio * encoderEPR);
        return rightRPM;
    }

    /**
     * @return Updates the RPM value of the left shooter motor
     */
    public double getLeftRPM() {
        leftRPM = shootMotorL.getSelectedSensorVelocity() * 600.0 / (leftRatio * encoderEPR);
        return leftRPM;
    }

    /**
     * Checks if both of the rpms are greater than the rpm threshold
     */
    public boolean atSpeed() {
        return ((getRightRPM() >= rpmThreshold) && (getLeftRPM() >= rpmThreshold));
    }

    // TODO test this method functionality
    /**
     * Checks if the motor voltages are greater than the acceptable voltage threshold
     */
    public boolean voltageSpike() {
        return ((shootMotorR.getBusVoltage() >= vThreshold) || (shootMotorL.getBusVoltage() >= vThreshold));
    }

    /**
     * Stops the shooter
     */
    public void stopShooter() {
        shootMotorR.stopMotor();
        shootMotorL.stopMotor();
    }
}