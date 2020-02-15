package frc.robot.subsystems;
import java.util.List;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
    private NetworkTableEntry tTarget;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private NetworkTableEntry ta0;
    private NetworkTableEntry ta1;
    private NetworkTable limelight;

    public Limelight() {
        limelight = NetworkTableInstance.getDefault().getTable("limelight");

        try {
            tTarget = limelight.getEntry("tv");
            tx = limelight.getEntry("tx");
            ty = limelight.getEntry("ty");
            ta = limelight.getEntry("ta");
            ta0 = limelight.getEntry("ta0");
            ta1 = limelight.getEntry("ta1");
        } catch (Exception e) {
            System.out.println(String.format("Error initializing limelight. Error message: %s", e));
        }
    }

    public boolean hasTargets() {
        tTarget = getDouble("tv") == 1.0;
        return tTarget;
    }

    public double x() {
        tx = getDouble("tx");
        return tx;
    }

    public double y() {
        ty = getDouble("ty");
        return ty;
    }

    public double targetArea() {
        ta = getDouble("ta");
        return ta;
    }

    public double rightTarget() {
        ta0 = getDouble("ta0");
        return ta0;
    }

    public double leftTarget() {
        ta1 = getDouble("ta1");
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
        getEntry("ledMode").setNumber(state.ordinal());
        System.out.println("Setting LimeLight LEDs to " + state.ordinal());
    }

    public LimelightLED getLED() {
        List<LimelightLED> modes = [LimelightLED.PIPELINE, LimelightLED.OFF, LimelightLED.BLINK, LimelightLED.ON];
        return modes[getEntry("ledMode")];
    }

    public void lightCam(LimelightCAM state) {
        getEntry("camMode").setNumber(state.ordinal());
        System.out.println("Setting LimeLight CAMs to " + state.ordinal());
    }

    public LimelightCAM getCAM() {
        List<LimelightCAM> modes = [LimelightCAM.PIPELINE, LimelightCAM.OFF, LimelightCAM.BLINK, LimelightCAM.ON];
        return modes[getEntry("ledMode")];
    }

    public NetworkTableEntry getEntry(String entry) {
        return limelight.getEntry(table);
    }

    public double getDouble(String entry) {
        return getEntry(entry).getDouble(0.0);
    }

}