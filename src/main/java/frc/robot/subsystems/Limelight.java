package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

    /**
     * Limelight's camera states
     */
    public enum CAM {
        VISION,
        DRIVER
    }

    /**
     * Limelight's LED states
     */
    public enum LED {
        PIPELINE,
        OFF,
        BLINK,
        ON
    }

    private NetworkTable limelight;

    /**
     * Initialize the Limelight file
     */
    public Limelight() {

        limelight = NetworkTableInstance.getDefault().getTable("limelight");

        try {
            getDouble("tv");
            getDouble("tx");
            getDouble("ty");
            getDouble("ta");
        } catch (Exception e) {
            System.out.println(String.format("Error initializing limelight. Error message: %s", e));
        }
    }

    /**
     * @return Whether the limelight has any valid targets
     */
    public boolean hasTargets() {
        return getDouble("tv") == 1.0;
    }

    /**
     * @return Horizontal Offset From Crosshair To Target (-29.8 to 29.8 degrees)
     */
    public double x() {
        return getDouble("tx");
    }

    /**
     * @return Vertical Offset From Crosshair To Target (-24.85 to 24.85 degrees)
     */
    public double y() {
        return getDouble("ty");
    }

    /**
     * @return Target Area (0% of image to 100% of image)
     */
    public double targetArea() {
        return getDouble("ta");
    }

    /**
     * Turn on Limelight's LEDs
     */
    public void turnOnLED() {
        setLEDMode(LED.ON);
    }

    /**
     * Turn of Limelight's LEDs
     */
    public void turnOffLED() {
        setLEDMode(LED.OFF);
    }

    /**
     * Set the Limelight camera to tracking (vision) mode
     */
    public void turnOnCam() {
        setCameraMode(CAM.VISION);
    }

    /**
     * Set the Limelight camera to driver mode (no filter)
     */
    public void turnOffCam() {
        setCameraMode(CAM.DRIVER);
    }

    /**
     * @param state what to set the limelight LED mode to
     */
    private void setLEDMode(LED state) {
        // Goes into the Limelight network tables and changes the LED mode value
        limelight.getEntry("ledMode").setNumber(state.ordinal());
        System.out.println("Setting LimeLight LEDs to " + state.ordinal());
    }

    /**
     * Get the state of the LEDs
     * TODO: This method isn't actually used anywhere
     */
    public LED getLED() {
        return LED.values()[(int) getDouble("ledMode")];
    }

    /**
     * @param state what to set the limelight camera mode to
     */
    private void setCameraMode(CAM state) {
        // Goes into the Limelight network tables and changes the cam mode value
        limelight.getEntry("camMode").setNumber(state.ordinal());
        System.out.println("Setting LimeLight CAMs to " + state.ordinal());
    }

    /**
     * @return the state of the Limelight camera
     * TODO: This method isn't actually used anywhere
     */
    public CAM getCAM() {
        return CAM.values()[(int) getDouble("ledMode")];
    }

    /**
     * @param entry what to check the Limelight network tables for
     * @return The value stored in the network table entry.
     * If it doesn't exist, return 0.0
     */
    private double getDouble(String entry) {
        return limelight.getEntry(entry).getDouble(0.0);
    }
}