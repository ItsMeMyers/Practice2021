package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
    private WPI_TalonFX turretMotor;

    private double spinPower;

    DigitalInput limitR;
    DigitalInput limitL;

    public Turret() {
        turretMotor = new WPI_TalonFX(Constants.turretMotor);

        limitR = new DigitalInput(Constants.limitSwitchR);
        limitL = new DigitalInput(Constants.limitSwitchL);

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

    public boolean getLimitR() {
        return limitR.get();
    }

    public boolean getLimitL() {
        return limitL.get();
    }

    /**
     * Stops the turret motor.
     */
    public void stopTurret() {
        spinPower = 0.0;
        turretMotor.stopMotor();
    }
}