package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Limelight.CAM;
import frc.robot.subsystems.Limelight.LED;

public class TargetEntity extends CommandBase {

	private final Limelight limelight;
	private final Turret turret;
	private final XboxController gamepad;

	private final double powerConstant = 0.025; // Proportional control constant to determine the power
	private final double minimumPower = 0.05; // Minimal power to send
	private final double threshold = 0.15; // The threshold in which the turret actually needs to move

	private double power; // Updated by the Limelight camera
	private double additionalPower; // Updated by the right Joystick on the gamepad
	private double turretPower; // Updated by the LimeLight camera; equal to power + additionalPower

	/**
	 * 1. Turns on LimeLight's LEDs and starts its targeting. <br>
	 * 2. Use the Up arrow on the POV to enable this command. <br>
	 * 3. Use the Down arrow to disable it. <br>
	 * 4. If LimeLight doesn't have a target, then set power, additionalPower, and
	 * turretPower to 0.0. <br>
	 * 5. If LimeLight does have a target, then: <br>
	 * 6. Get the tx value from NetworkTables. <br>
	 * 7. Turn the value into a speed by: 8. Inverting the X value (because if it's
	 * negative, then get a positive value which will make the turret turn right
	 * towards the center). <br>
	 * 9. Multiplying it by the proportional control constant. <br>
	 * 10. If the x value is not within the threshold and the power is less than the
	 * minimum power, set the power to the minimum power. <br>
	 * 11. If the x value is within the threshold, set the power to 0. <br>
	 * 12. Get the additional power from the game pad's left joystick's Y value.
	 * <br>
	 * 13. If the total power is greater than 1, set it to 1. <br>
	 * 14. If the total power is less than -1, set it to -1. <br>
	 * 15. Pass the power to the motor. <br>
	 * 16. Print the values to SmartDashboard. <br>
	 */
	public TargetEntity(Limelight ll, Turret trrt, XboxController gmpd) {
		turret = trrt;
		limelight = ll;
		gamepad = gmpd;
		power = 0;
		additionalPower = 0;
		turretPower = 0;
		addRequirements(limelight, turret);
	}

	/**
	 * Execute one iteration of the TargetEntity command (For multiple iterations,
	 * call multiple times) Only executes if it is enabled by the game pad
	 */
	@Override
	public void execute() {
		limelight.setLED(LED.ON); // Turn on the LED's if they haven't been turned on before
		limelight.setCAM(CAM.VISION); // Turn on vision mode if it wasn't turned on before

		boolean hasTarget = limelight.hasTarget();
		if (hasTarget) { // Whether Limelight detects a target
			// The number of degrees the target is off center horizontally
			double x = limelight.x();
			// The number of degrees Limelight needs to shift by to be centered
			double degreesToCenter = -x;

			// Instead of waiting for the target to go off the screen, center the target
			// This value will be negative if it needs to go right and positive if it needs
			// to go left
			// We multiply by a constant because the motor doesn't need that much power so
			// it doesn't go way too fast
			power = powerConstant * degreesToCenter;
			// If the power is too small and tx is within the threshold,
			// increase it to +/- 0.05 so the turret actually moves
			if (x > threshold && power > -minimumPower) {
				power = -minimumPower;
			} else if (x < -threshold && power < minimumPower) {
				power = minimumPower;
			} else if (x < threshold && x > -threshold) {
				// Target is within the threshold, so do nothing
				power = 0.0;
			}

			// To increase the speed of the turret, you can push the Right Joystick on
			// the gamepad to add additional power
			additionalPower = Math.abs(gamepad.getY(Hand.kRight)) * Math.signum(power);
			// Total power of the turret
			turretPower = power + additionalPower;
			// Makes sure the power does not get higher than 1 or less than -1
			if (turretPower > 1) {
				turretPower = 1;
			} else if (turretPower < -1) {
				turretPower = -1;
			}
		} else {
			// If Limelight does not detect a target then it sets the turret power to 0.
			power = 0.0;
			additionalPower = 0.0;
			turretPower = 0.0;
		}

		turret.setSpinPower(turretPower);
		// Puts values on the Smart Dashboard
		SmartDashboard.putBoolean("Target.TargetIdentified", hasTarget);
		SmartDashboard.putNumber("Target.Power", power);
		SmartDashboard.putNumber("Target.AddPower", additionalPower);
		SmartDashboard.putNumber("Target.TurretPower", turretPower);
		SmartDashboard.putNumber("GamePad.POV", gamepad.getPOV());
	}

	/**
	 * If the the down arrow on the POV is pressed, the command is done. Stops
	 * targeting and turns off the LEDs so people don't get blinded.
	 * 
	 * @param interrupted Whether the command was interrupted/canceled
	 */
	@Override
	public void end(boolean interrupted) {
		limelight.setLED(LED.OFF);
	}
}
