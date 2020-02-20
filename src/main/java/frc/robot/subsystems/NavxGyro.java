package frc.robot.subsystems;

import frc.robot.lib.RioLogger;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.SPI.Port;

public class NavxGyro extends AHRS {
	private PIDController turnController;
	private double toleranceDegrees = 2.0;
	private double rotateToAngleRate;

	public NavxGyro(Port spi_port_id) throws RuntimeException {
		super(spi_port_id);
	}

	public void setToleranceDegrees(double toleranceDegrees) {
		this.toleranceDegrees = toleranceDegrees;
	}

	public double getRotateToAngleRate() {
		return rotateToAngleRate;
	}

	public void calibrate() {
		try {
			int maxCalibrationPasses = 20;
			for (int iCalibrationPasses = 0; iCalibrationPasses < maxCalibrationPasses; iCalibrationPasses++) {
				if (!Constants.gyro.isCalibrating())
					break;
				RioLogger.log("robotInit() gyro is calibrating, pass " + iCalibrationPasses);
				try {
					Thread.sleep(100); // Sleep 1/10 of second
				} catch (InterruptedException e) {
					RioLogger.errorLog("navX-MXP initialization thread exception " + e);
				}
			}

			RioLogger.log("robotInit() gyro is calibrating " + Constants.gyro.isCalibrating());
			if (!Constants.gyro.isCalibrating())
				Constants.gyro.zeroYaw();
			RioLogger.log("robotInit() currentYaw " + Constants.gyro.getYaw());
		} catch (RuntimeException ex) {
			RioLogger.errorLog("navX-MXP initialization error " + ex);
		}
	}
}