package frc.robot.subsystems;

import static frc.robot.Constants.FeederConstants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {

    private final WPI_TalonSRX feederMotor1;
    private final TimeOfFlight ballPresentSensor;

    // Number of balls the feeder is storing
    private int ballCounter = 0;

    /**
     * This is the subsystem that sends the balls to the shooter.
     * It also stores the balls that come in from the Intake.
     */
    public Feeder() {

        // When the motors are in neutral mode the motors will keep moving easily (coast)
        ballPresentSensor = new TimeOfFlight(feederBallPresentSensor);

        //Set the distance mode of the TOF sensor
        ballPresentSensor.setRangingMode(TimeOfFlight.RangingMode.Medium, 1.0);
    }

    /**
     * Runs the feeder. This feeds the balls to the shooter.
     */
    public void run(boolean fullSpeed) {
    }

    /**
     * Runs the feeder in reverse. This clears the feeder
     */
    public void reverse() {
    }

    /**
     * Stops the feeder.
     */
    public void stop() {
    }

    // TODO: Unimplemented method addBall
    /**
     * Increments the ball count by 1.
     */
    public void addBall() {
    }

    /**
     * Decrements the ball count by 1.
     */
    public void shotBall() {
    }

    public int getCounter() {
        return ballCounter;
    }

    /**
     * Returns whether or not the TOF sensor currently sees a ball
     */
    public boolean getBallPresent() {
        if(ballPresentSensor == null)
            return false;
        double range = ballPresentSensor.getRange();
        boolean ballPresent = false;
        if (range <= feederBallPresentThreshold) {
            ballPresent = true;
        }
        return ballPresent;
    }
}