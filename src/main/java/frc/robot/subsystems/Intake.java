package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    
    private Solenoid intakeSolenoid;
    private WPI_TalonSRX intakeMotor;

    private double in = -1.0;
    private double out = 1.0;

    private boolean state = false;

    /**
     * The intake pulls the balls from the ground into the robot.
     * The balls are then stored in the feeder to wait to be shot.
     * The intake can be stowed in or stowed out.
     * The direction of the intake can also be switched.
     */
    public Intake() {
        intakeMotor = new WPI_TalonSRX(Constants.intakeMotor);
        intakeSolenoid = new Solenoid(Constants.intakeSolenoid);

        intakeMotor.setNeutralMode(NeutralMode.Brake);
        intakeSolenoid.set(true);
    }

    /**
    * 1. Changes the intake direction to take in balls from the ground. <br>
    * 2. Hold the right trigger to activate the command. <br>
    * 3. Balls from the intake are then stored in the feeder. <br>
    * 4. This changes the intake direction, not whether it is stowed in or not.
    */
    public void runIn() {
        intakeMotor.set(in);
    }

    /**
     * Pushes out a ball out from the feeder.
     */
    public void runOut() {
        intakeMotor.set(out);
    }

    /**
    * 1. Changes the intake direction to take in balls from the ground. <br>
    * 2. Hold the right button to activate the command. <br>
    * 3. Balls can be pushed out onto the ground. <br>
    * 4. This changes the intake direction, not whether it is stowed out or not.
    */
    public void stopMotor() {
        intakeMotor.set(0.0);
    }

    /**
     * Brings the intake system in and out.
     * It can be stowed away or out.
     * Note: Does not change direction of intake system
     */
    public void toggle() {
        intakeSolenoid.set(!state);
    }
}