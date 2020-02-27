package frc.robot.subsystems;

import static frc.robot.Constants.TurretConstants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
    private final WPI_TalonFX turretMotor;

    private double spinPower;

    public Turret() {
        turretMotor = new WPI_TalonFX(turretMotorPort);

        // When the motor is in neutral mode the motor will keep moving easily (coast)
        turretMotor.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * Sets the spin power of the turret. This makes sure that it is
     * not less than -1 or greater than 1.
     */
    public void setSpinPower(double spinPwr) {
        if (spinPwr > 1.0) {
            spinPwr = 1.0;
        } else if (spinPwr < -1.0) {
            spinPwr = -1.0;
        }
        this.spinPower = spinPwr;
        turretMotor.set(spinPower);
    }

    /**
     * Stops the turret motor.
     */
    public void stopTurret() {
        spinPower = 0.0;  
        turretMotor.stopMotor();
    }
}