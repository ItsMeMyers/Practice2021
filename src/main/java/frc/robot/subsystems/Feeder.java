package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {

    private WPI_TalonSRX feederMotor1;
    private WPI_TalonSRX feederMotor2;

    private int ballCounter = 0;

    private double speedLimiter = 0.7;

    /**
     * This is the subsystem that sends the balls to the shooter.
     * It also stores the balls that come in from the Intake.
     */
    public Feeder() {
        feederMotor1 = new WPI_TalonSRX(Constants.feederMotor1);
        feederMotor2 = new WPI_TalonSRX(Constants.feederMotor2);

        // When the motors are in neutral mode the motors will keep moving easily (coast)
        feederMotor1.setNeutralMode(NeutralMode.Coast);
        feederMotor2.setNeutralMode(NeutralMode.Coast);
    }

    /**
     * Runs the feeder. This feeds the balls to the shooter.
     */
    public void run() {
        feederMotor1.set(speedLimiter);
        feederMotor2.set(speedLimiter * Constants.INVERT_MOTOR);
    }

    /**
     * Stops the feeder.
     */
    public void stop() {
        feederMotor1.stopMotor();
        feederMotor2.stopMotor();
    }

    // TODO: These addBall and shotBall methods should probably be implemented somewhere
    /**
     * Increments the ball count by 1.
     */
    public void addBall() {
        ballCounter++;
    }

    /**
     * Decrements the ball count by 1.
     */
    public void shotBall() {
        ballCounter--;
    }

    public int getCounter() {
        return ballCounter;
    }
}