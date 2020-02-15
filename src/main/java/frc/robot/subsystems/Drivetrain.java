package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    // The motors on the left side of the drive.
    private final SpeedControllerGroup leftMotors =
    new SpeedControllerGroup(
        new WPI_TalonFX(Constants.l1Motor),
        new WPI_TalonFX(Constants.l2Motor),
        new WPI_TalonFX(Constants.l3Motor));

    // The motors on the right side of the drive.
    private final SpeedControllerGroup rightMotors = 
    new SpeedControllerGroup(
        new WPI_TalonFX(Constants.r1Motor),
        new WPI_TalonFX(Constants.r2Motor),
        new WPI_TalonFX(Constants.r3Motor));

    // motor properties
    private double rightPower = 0.0;
    private double leftPower = 0.0;

    private boolean invertRight = false;
    private boolean invertLeft = false;

    // The robot's drive
    private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotors, rightMotors);

    // The left-side drive encoder
    private final Encoder leftEncoder =
    new Encoder(Constants.kLeftEncoderPorts[0], Constants.kLeftEncoderPorts[1],
                Constants.kLeftEncoderReversed);

    // The right-side drive encoder
    private final Encoder rightEncoder =
    new Encoder(Constants.kRightEncoderPorts[0], Constants.kRightEncoderPorts[1],
                Constants.kRightEncoderReversed);

    // The gyro sensor
    private AHRS gyro = new AHRS(SPI.Port.kMXP);

    // Odometry class for tracking robot pose
    private final DifferentialDriveOdometry odometry;

    public Drivetrain() {
        // Sets the distance per pulse for the encoders
        leftEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);
        rightEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);

        resetEncoders();
        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    }

    public double getLeftPower() {
        return leftPower;
    }

    public double getRightPower() {
        return rightPower;
    }

    /**
     * First, it determines if the power is greater than 1 or less than 1.
     * If the power is, then it changes the power so it is within [-1, 1].
     * Then, it takes the square root of the power.
     * Then it sets the appropiate power value in the class to the new power value.
     * @param pwr the power to set it to
     * @param isRight if this is the right motor power or not
     */
    public void setPower(double pwr, boolean isRight) {
        if (pwr > 1.0) {
            pwr = 1.0;
        } else if (pwr < -1.0) {
            pwr = -1.0;
        }

        // Sqrt power scalar
        if (pwr < 0) {
            pwr = -1.0 * (Math.sqrt(-1.0 * pwr));
        } else {
            pwr = Math.sqrt(pwr);
        }

        if (isRight) {
            this.rightPower = pwr;
        } else {
            this.leftPower = pwr;
        }
    }

    public void drive() {
        drive(rightPower, leftPower);
    }
    
    public void drive(double rightP, double leftP) {
        
        if (invertRight) {
            rightP *= Constants.INVERT_MOTOR;
        }
        
        if (invertLeft) {
            leftP *= Constants.INVERT_MOTOR;
        }
        
        rightMotors.set(rightP);
        leftMotors.set(leftP);
    }

    public void stop() {
        rightPower = 0.0;
        leftPower = 0.0;
        leftMotors.stopMotor();
        rightMotors.stopMotor();
    }

    @Override
    public void periodic() {
        // Update the odometry in the periodic block
        odometry.update(Rotation2d.fromDegrees(getHeading()), leftEncoder.getDistance(),
                        rightEncoder.getDistance());
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    /**
     * Returns the current wheel speeds of the robot.
     *
     * @return The current wheel speeds.
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
    }

    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */
    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
    }

    /**
     * Drives the robot using arcade controls.
     *
     * @param fwd the commanded forward movement
     * @param rot the commanded rotation
     */
    public void arcadeDrive(double fwd, double rot) {
        diffDrive.arcadeDrive(fwd, rot);
    }

    /**
     * Controls the left and right sides of the drive directly with voltages.
     *
     * @param leftVolts  the commanded left output
     * @param rightVolts the commanded right output
     */
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        leftMotors.setVoltage(leftVolts);
        rightMotors.setVoltage(-rightVolts);
    }

    /**
     * Resets the drive encoders to currently read a position of 0.
     */
    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
    }

    /**
     * Gets the left drive encoder.
     *
     * @return the left drive encoder
     */
    public Encoder getLeftEncoder() {
        return leftEncoder;
    }

    /**
     * Gets the right drive encoder.
     *
     * @return the right drive encoder
     */
    public Encoder getRightEncoder() {
        return rightEncoder;
    }

    /**
     * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
     *
     * @param maxOutput the maximum output to which the drive will be constrained
     */
    public void setMaxOutput(double maxOutput) {
        diffDrive.setMaxOutput(maxOutput);
    }

    /**
     * Zeroes the heading of the robot.
     */
    public void zeroHeading() {
        gyro.reset();
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from 180 to 180
     */
    public double getHeading() {
        return Math.IEEEremainder(gyro.getAngle(), 360) * (Constants.kGyroReversed ? -1.0 : 1.0);
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return gyro.getRate() * (Constants.kGyroReversed ? -1.0 : 1.0);
    }
}