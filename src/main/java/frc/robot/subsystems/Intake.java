package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    
    private Solenoid panelSolenoid;
    private WPI_TalonSRX intakeMotor;

    private double in = -1.0;
    private double out = 1.0;

    private boolean state = false;

    /**
     * The intake pulls the balls from the ground into the robot.
     * The balls are then stored in the feeder to wait to be shot.
     */
    public Intake() {
        intakeMotor = new WPI_TalonSRX(Constants.intakeMotor);
        panelSolenoid = new Solenoid(Constants.panelSolenoid);

        intakeMotor.setNeutralMode(NeutralMode.Brake);
        panelSolenoid.set(true);
    }

    /**
     * Takes a ball in from the ground.
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

    public void stopMotor() {
        intakeMotor.set(0.0);
    }

    public void toggle() {
        panelSolenoid.set(!state);
    }
}