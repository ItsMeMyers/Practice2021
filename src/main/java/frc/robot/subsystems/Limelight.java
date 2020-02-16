package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
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
            getDouble("ta0");
            getDouble("ta1");
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
     * Get the target area on the right
     */
    public double rightTarget() {
        return getDouble("ta0");
    }
    // TODO: Are you sure this is what ta0 and ta1 means? Also they aren't even used anywhere
    // https://docs.limelightvision.io/en/latest/networktables_api.html#advanced-usage-with-raw-contours
    /**
     * Get the target area on the left
     */
    public double leftTarget() {
        return getDouble("ta1");
    }

    /**
     * Turn on Limelight's leds
     */
    public void turnOnLED() {
        lightLED(LED.ON);
    }

    /**
     * Turn of Limelight's leds
     */
    public void turnOffLED() {
        lightLED(LED.OFF);
    }

    /**
     * Set the Limelight camera to tracking mode
     */
    public void turnOnCam() {
        lightCam(CAM.VISION);
    }

    /**
     * Set the Limelight camera to driver mode
     */
    public void turnOffCam() {
        lightCam(CAM.DRIVER);
    }

    /**
     * Set the state of the led
     */
    private void lightLED(LED state) {
        setNumber("ledMode", state.ordinal());
        System.out.println("Setting LimeLight LEDs to " + state.ordinal());
    }

    /**
     * Get the state of the led's
     * TODO: This method isn't actually used anywhere
     */
    public LED getLED() {
        LED[] modes = {LED.PIPELINE, LED.OFF, LED.BLINK, LED.ON};
        return modes[(int) getDouble("ledMode")];
    }

    /**
     * Set the state of the camera
     */
    public void lightCam(CAM state) {
        setNumber("camMode", state.ordinal());
        System.out.println("Setting LimeLight CAMs to " + state.ordinal());
    }

    /**
     * Get the state of the camera
     * TODO: This method isn't actually used anywhere
     */
    public CAM getCAM() {
        CAM[] modes = {CAM.VISION, CAM.DRIVER};
        return modes[(int) getDouble("ledMode")];
    }

    /**
     * Set the number of an entry on the Limelight table some double
     */
    public void setNumber(String entry, double state) {
        getEntry(entry).setNumber(state);
    }

    /**
     * Get the network table entry
     */
    private NetworkTableEntry getEntry(String table) {
        return limelight.getEntry(table);
    }

    /**
     * Get the double stored in a network table entry
     */
    private double getDouble(String entry) {
        return getEntry(entry).getDouble(0.0);
    }
}