package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    
    Solenoid panelSolenoid;

    private boolean up = true;
    private boolean down = false;

    public Intake() {
        panelSolenoid = new Solenoid(Constants.panelSolenoid);
        panelSolenoid.set(up);
    }

    public void down() {
        panelSolenoid.set(down);
    }

    public void up() {
        panelSolenoid.set(up);
    }
}