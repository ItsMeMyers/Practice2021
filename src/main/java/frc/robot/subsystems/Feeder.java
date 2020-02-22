package frc.robot.subsystems;

import static frc.robot.Constants.FeederConstants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {

    private final WPI_TalonSRX feederMotor1;
    private final WPI_TalonSRX feederMotor2;
    //private final TimeOfFlight feederBallPresentSensor;

    // Number of balls the feeder is storing
    private int ballCounter = 0;

    // Makes sure the speed does not increase over this number
    private double speedLimiter = 0.7;

    /**
     * This is the subsystem that sends the balls to the shooter.
     * It also stores the balls that come in from the Intake.
     */
    public Feeder() {

        feederMotor1 = new WPI_TalonSRX(motor1Port);
        feederMotor2 = new WPI_TalonSRX(motor2Port);

        // When the motors are in neutral mode the motors will keep moving easily (coast)
        feederMotor1.setNeutralMode(NeutralMode.Coast);
        feederMotor2.setNeutralMode(NeutralMode.Coast);

        //Set the distance mode of the TOF sensor
        //feederBallPresentSensor.setRangingMode(TimeOfFlight.RangingMode.Medium, 1.0);
    }

    /**
     * Runs the feeder. This feeds the balls to the shooter.
     */
    public void run() {
        feederMotor1.set(speedLimiter);
        feederMotor2.set(speedLimiter * INVERT_MOTOR);
    }

    /**
     * Stops the feeder.
     */
    public void stop() {
        feederMotor1.stopMotor();
        feederMotor2.stopMotor();
    }

    // TODO: Unimplemented method addBall
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

    /**
     * Returns whether or not the TOF sensor currently sees a ball
     */
    // public boolean ballPresent() {
    //     double range = feederBallPresentSensor.getRange();
    //     boolean ballPresent = false;
    //     if (range <= feederBallPresentThreshold) {
    //         ballPresent = true;
    //     }
    //     return ballPresent;
    // }
}