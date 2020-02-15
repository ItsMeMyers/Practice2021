package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

    private boolean initialized = false;
    private NetworkTableEntry tTarget = null;
    private NetworkTableEntry tx = null;
    private NetworkTableEntry ty = null;
    private NetworkTableEntry ta = null;
    private NetworkTableEntry ta0 = null;
    private NetworkTableEntry ta1 = null;

    public Limelight() {

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

        try {
            tTarget = table.getEntry("tv");
            tx = table.getEntry("tx");
            ty = table.getEntry("ty");
            ta = table.getEntry("ta");
            ta0 = table.getEntry("ta0");
            ta1 = table.getEntry("ta1");

        } catch (Exception e) {
            System.out.println(String.format("Error initializing limelight. Error message: %s", e));
        }
        initialized = true;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public boolean hasTargets() {
        boolean hits = false;
        if (isInitialized()) {
            hits = (tTarget.getDouble(0.0) == 1.0);
        }
        return hits;
    }

    public double x() {
        double dx = 0;
        if (isInitialized()) {
            dx = tx.getDouble(0.0);
        }
        return dx;
    }

    public double y() {
        double dy = 0;
        if (isInitialized()) {
            dy = ty.getDouble(0.0);
        }
        return dy;
    }

    public double targetArea() {
        double dArea = 0;
        if (isInitialized()) {
            dArea = ta.getDouble(0.0);
        }
        return dArea;
    }

    public double rightTarget() {
        double dArea = 0;
        if (isInitialized()) {
            dArea = ta0.getDouble(0.0);
        }
        return dArea;
    }

    public double leftTarget() {
        double dArea = 0;
        if (isInitialized()) {
            dArea = ta1.getDouble(0.0);
        }
        return dArea;
    }

    public void turnOnLED() {
        lightLED(LimelightLED.ON);
    }

    public void turnOffLED() {
        lightLED(LimelightLED.OFF);
    }

    public void turnOnCam() {
        lightCam(LimelightCAM.VISION);
    }

    public void turnOffCam() {
        lightCam(LimelightCAM.DRIVER);
    }

    public void lightLED(LimelightLED state) {

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        table.getEntry("ledMode").setNumber(state.ordinal());
        System.out.println("Setting LimeLight LEDs to " + state.ordinal());
    }

    public void lightCam(LimelightCAM state) {

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        table.getEntry("camMode").setNumber(state.ordinal());
        System.out.println("Setting LimeLight CAMs to " + state.ordinal());
    }

}