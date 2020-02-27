package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    private final SpeedControllerGroup leftMotors = new SpeedControllerGroup(new WPI_TalonFX(L1MotorPort), new WPI_TalonFX(L2MotorPort), new WPI_TalonFX(L3MotorPort)); // The motors on the left side of the drive.

    private final SpeedControllerGroup rightMotors = new SpeedControllerGroup(new WPI_TalonFX(R1MotorPort), new WPI_TalonFX(R2MotorPort), new WPI_TalonFX(R3MotorPort)); // The motors on the right side of the drive.

    private double rightPower = 0.0;
    private double leftPower = 0.0;

    private boolean invertRight = false; // Whether or not to invert the right motor
    private boolean invertLeft = false; // Whether or not to invert the left motor

    private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotors, rightMotors); // The robot's drive

    private final Encoder leftEncoder = new Encoder(leftEncoderPorts[0], leftEncoderPorts[1], leftEncoderReversed); // The left-side drive encoder

    private final Encoder rightEncoder = new Encoder(rightEncoderPorts[0], rightEncoderPorts[1], rightEncoderReversed); // The right-side drive encoder

    private final AHRS gyro = new AHRS(SPI.Port.kMXP); // The gyro sensor

    private final DifferentialDriveOdometry odometry; // Odometry class for tracking robot pose

    /**
     * Method use to drive the robot
     */
    public Drivetrain() {
        // Sets the distance per pulse for the encoders
        leftEncoder.setDistancePerPulse(encoderDistancePerPulse);
        rightEncoder.setDistancePerPulse(encoderDistancePerPulse);

        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
        resetEncoders();
        zeroHeading();
    }

    /**
     * Get the left motor power
     * @return The left motor power
     */
    public double getLeftPower() {
        return leftPower;
    }

    /**
     * Get the right motor power
     * @return The right motor power
     */
    public double getRightPower() {
        return rightPower;
    }

    /**
     * Scale the power
     * @param pwr Value to scale
     * @return The scaled value
     */
    private double scalePower(double pwr) {
        return pwr > 1.0 ? 1.0 : pwr < -1.0 ? -1.0 : pwr;
    }

    /**
     * Set the right power
     * @param pwr Value to set the power to
     */
     public void setRightPower(double pwr) {
         rightPower = scalePower(pwr);
     }

     /**
      * Set the left power
      * @param pwr Value to set the power to
      */
     public void setLeftPower(double pwr) {
         leftPower = scalePower(pwr);
     }

     /**
      * Stop the right motor
      */
     public void stopRightMotor() {
         rightPower = 0.0;
         rightMotors.stopMotor();
     }

    /**
     * Stop the left motor
     */
    public void stopLeftMotor() {
        leftPower = 0.0;
        leftMotors.stopMotor();
    }

    /**
     * Drive with default values from the joysticks
     */
    public void drive() {
        drive(rightPower, leftPower);
    }
    
    /**
     * Drive with custom values
     * @param rightP Right motor power
     * @param leftP Left motor power
     */
    public void drive(double rightP, double leftP) {
        if (invertRight) {
            rightP *= INVERT_MOTOR;
        }
        
        if (invertLeft) {
            leftP *= INVERT_MOTOR;
        }
        
        rightMotors.set(rightP);
        leftMotors.set(leftP);
    }

    /**
     * Stops the drive train
     */
    public void stop() {
        stopRightMotor();
        stopLeftMotor();
    }

    /**
     * Called periodically by the CommmandScheduler
     */
    @Override
    public void periodic() {
        odometry.update(Rotation2d.fromDegrees(getHeading()), leftEncoder.getDistance(), rightEncoder.getDistance()); // Update the odometry in the periodic block (gets location on field)
    }

    /**
     * Returns the currently-estimated pose of the robot
     * @return The pose. (position on the field)
     */
    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    /**
     * Returns the current wheel speeds of the robot
     * @return The current wheel speeds.
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
    }

    /**
     * Resets the odometry to a default pose Pose2d(0, 0, new Rotation2d(0))
     * Resets the encoders, also automatically resets heading
     */
    public void resetOdometry() {
        resetOdometry(new Pose2d(0, 0, new Rotation2d(0)));
    }

    /**
     * Resets the odometry to the specified pose
     * Resets the encoders, also automatically resets heading
     * @param pose The pose to which to set the odometry
     */
    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
    }

    /**
     * Drives the robot using arcade controls
     * @param fwd the commanded forward movement
     * @param rot the commanded rotation
     */
    public void arcadeDrive(double fwd, double rot) {
        diffDrive.arcadeDrive(fwd, rot);
    }

    /**
     * Controls the left and right sides of the drive directly with voltages
     * @param leftVolts the commanded left output
     * @param rightVolts the commanded right output
     */
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        leftMotors.setVoltage(leftVolts);
        rightMotors.setVoltage(-rightVolts);
    }

    /**
     * Resets the drive encoders to currently read a position of 0
     */
    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    /**
     * Gets the average distance of the two encoders
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
    }

    /**
     * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly
     * @param maxOutput the maximum output to which the drive will be constrained
     */
    public void setMaxOutput(double maxOutput) {
        diffDrive.setMaxOutput(maxOutput);
    }

    /**
     * Zeroes the heading of the robot
     */
    public void zeroHeading() {
        gyro.reset();
    }

    /**
     * Returns the heading of the robot
     * @return the robot's heading in degrees, from -180 to 180
     */
    public double getHeading() {
        return Math.IEEEremainder(gyro.getAngle(), 360) * (GYRO_REVERSED ? -1.0 : 1.0);
    }

    /**
     * Returns the turn rate of the robot
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return gyro.getRate() * (GYRO_REVERSED ? -1.0 : 1.0);
    }
}