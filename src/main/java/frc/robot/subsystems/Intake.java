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

    public Intake() {
        intakeMotor = new WPI_TalonSRX(Constants.intakeMotor);
        panelSolenoid = new Solenoid(Constants.panelSolenoid);

        intakeMotor.setNeutralMode(NeutralMode.Brake);
        panelSolenoid.set(true);
    }

    public void runIn() {
        intakeMotor.set(in);
    }

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