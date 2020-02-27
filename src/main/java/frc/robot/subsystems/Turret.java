package frc.robot.subsystems;

import static frc.robot.Constants.TurretConstants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
    private final WPI_TalonSRX turretMotor;

    private double spinPower;
    private Solenoid turretSolenoid;
    private boolean folded = true;

    public Turret() {
        turretMotor = new WPI_TalonSRX(turretMotorPort);
        // When the motor is in neutral mode the motor will keep moving easily (coast)
        turretMotor.setNeutralMode(NeutralMode.Brake);
        turretSolenoid = new Solenoid(turretSolenoidPort);
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
        //If we are below 
        if (turretMotor.getStatorCurrent() < turretMotorHardStopCurrentThreshold) {
            turretMotor.set(spinPower);
        }
    }

    /**
     * Retract turret
     */
    public void foldTurret() {
        turretSolenoid.set(false);
        foled = true;
    }

    /**
     * Expand turret
     */
    public void unfoldTurret() {
        turretSolenoid.set(true);
        folded = false;
    }

    /**
     * Check if turret is folded down
     */
    public boolean getFolded() {
        return folded;
    }

    /**
     * Get motor at hardstop
     */
    public boolean turretAtHardStop() {
        return turretMotor.getStatorCurrent() >= turretMotorHardStopCurrentThreshold;
    }

    /**
     * Stops the turret motor.
     */
    public void stopTurret() {
        spinPower = 0.0;  
        turretMotor.stopMotor();
    }
}