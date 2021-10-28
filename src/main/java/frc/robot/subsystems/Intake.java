package frc.robot.subsystems;

import static frc.robot.Constants.IntakeConstants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    
    private final Solenoid intakeSolenoid;
    private final WPI_TalonSRX intakeBarMotor;

    private final WPI_TalonSRX intakeFunnel;
    private final WPI_TalonFX intakeLowerTower;
    private final TimeOfFlight ballPresentSensor;

    private final double inSpeed = 1.0;
    private final double inHalfSpeed = .3;
    private final double inHalfSpeedFunnel = .5;
    private final double outSpeed = 0.5;

    private boolean engaged = false;

    /**
     * The intake pulls the balls from the ground into the robot.
     * The balls are then stored in the feeder to wait to be shot.
     * The intake can be stowed in or stowed out.
     * The direction of the intake can also be switched.
     */

     //instanciate motors and solenoids
    public Intake() {
        ballPresentSensor = new TimeOfFlight(intakeBallPresentSensor);

        // When the motor is in neutral mode the motor will keep moving easily (coast)


        //Set the distance mode of the TOF sensor
        ballPresentSensor.setRangingMode(TimeOfFlight.RangingMode.Medium, 1.0);

    }

    public void runIntakeIn(boolean fullSpeed) {
        
    }

    public void runIntakeOut(boolean fullSpeed) {
    }

    public void stopIntake() {
    }

    public void runFunnelIn(boolean fullSpeed) {
    }

    public void runFunnelOut() {
    }

    public void stopFunnel() {
    }

    public void runLowerTowerIn(boolean fullSpeed) {
    }

    public void runLowerTowerOut() {
    }

    public void stopLowerTower() {
    }

    public void checkLowerTower(){
    }

    /**
    * 1. Changes the intake direction to take in balls from the ground. <br>
    * 2. Hold the right trigger to activate the command. <br>
    * 3. Balls from the intake are then stored in the feeder. <br>
    * 4. This changes the intake direction, not whether it is stowed in or not.
    */
    public void runAllIn() {
    }

    /**
    * 1. Changes the intake direction to take in balls from the ground. <br>
    * 2. Hold the right button to activate the command. <br>
    * 3. Balls can be pushed out onto the ground. <br>
    * 4. This changes the intake direction, not whether it is stowed out or not.
    */
    public void runAllOut() {
    }


    /**
    * 1. This command stows in the intake system and puts it out.
    * 2. Press the A button to toggle this command.
    * 3. This works by turning the intake solenoid on and off.
    * 4. It will toggle it from stowed in to out or vice versa.
    * 5. This is different from turning the intake direction from in and out.
    */
    public void toggle() {
    }

    /**
     * Allows ability of forcing intake to certain position (not just toggle)
     */
    public void forceTo(boolean override) {
        intakeSolenoid.set(override);
        engaged = override;
    }

    public void intakeUp(){
    }

    public void intakeDown(){
    }

    /**
     * Check if we are currently engaged
     */
    public boolean getEngaged() {
        return engaged;
    }

    /**
     * Returns whether or not the TOF sensor currently sees a ball
     */
    public boolean getBallPresent() {
        if(ballPresentSensor == null)
            return false;
        double range = ballPresentSensor.getRange();
        boolean ballPresent = false;
        if (range <= intakeBallPresentThreshold) {
            ballPresent = true;
        }
        return ballPresent;
    }
}