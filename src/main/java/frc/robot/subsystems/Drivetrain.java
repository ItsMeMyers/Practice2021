package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.GYRO_REVERSED;
import static frc.robot.Constants.DrivetrainConstants.INVERT_MOTOR;
import static frc.robot.Constants.DrivetrainConstants.encoderDistancePerPulse;
import static frc.robot.Constants.DrivetrainConstants.leftEncoderPorts;
import static frc.robot.Constants.DrivetrainConstants.leftEncoderReversed;
import static frc.robot.Constants.DrivetrainConstants.rightEncoderPorts;
import static frc.robot.Constants.DrivetrainConstants.rightEncoderReversed;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

   private WPI_TalonFX ltMotor;
   private WPI_TalonFX lmMotor;
   private WPI_TalonFX lbMotor;
   private WPI_TalonFX rtMotor;
   private WPI_TalonFX rmMotor;
   private WPI_TalonFX rbMotor;

    

    // The motors on the left side of the drive.
    // private final SpeedControllerGroup leftMotors =
    /* new SpeedControllerGroup(
        new WPI_TalonFX(LTMotorPort),
        new WPI_TalonFX(LMMotorPort),
        new WPI_TalonFX(LLMotorPort)); */

    // The motors on the right side of the drive.
   // private final SpeedControllerGroup rightMotors = 
    /* new SpeedControllerGroup(
        new WPI_TalonFX(RTMotorPort),
        new WPI_TalonFX(RMMotorPort),
        new WPI_TalonFX(RLMotorPort));
 */
    // motor properties
    private double rightPower = 0.0;
    private double leftPower = 0.0;

    private boolean invertRight = false; // Whether or not to invert the right motor
    private boolean invertLeft = false; // Whether or not to invert the left motor

    //private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotors, rightMotors); // The robot's drive

    private final Encoder leftEncoder = new Encoder(leftEncoderPorts[0], leftEncoderPorts[1], leftEncoderReversed); // The left-side drive encoder

    private final Encoder rightEncoder = new Encoder(rightEncoderPorts[0], rightEncoderPorts[1], rightEncoderReversed); // The right-side drive encoder

    private final AHRS gyro = new AHRS(SPI.Port.kMXP); // The gyro sensor

    private final DifferentialDriveOdometry odometry; // Odometry class for tracking robot pose

    /**
     * Method use to drive the robot
     */
    public Drivetrain() {

        ltMotor = new WPI_TalonFX(Constants.DrivetrainConstants.LTMotorPort);
        lmMotor = new WPI_TalonFX(Constants.DrivetrainConstants.LMMotorPort);
        lbMotor = new WPI_TalonFX(Constants.DrivetrainConstants.LLMotorPort);
        rtMotor = new WPI_TalonFX(Constants.DrivetrainConstants.RTMotorPort);
        rmMotor = new WPI_TalonFX(Constants.DrivetrainConstants.RMMotorPort);
        rbMotor = new WPI_TalonFX(Constants.DrivetrainConstants.RLMotorPort);

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
     * Set the right power
     * @param pwr Value to set the power to
     */
     public void setRightPower(double pwr) {
         if (pwr > 1.0) {
             rightPower = 1.0;
             return;
         } else if (pwr < -1.0) {
             rightPower = -1.0;
             return;
         }

         rightPower = pwr;
     }

     /**
      * Set the left power
      * @param pwr Value to set the power to
      */
     public void setLeftPower(double pwr) {
        if (pwr > 1.0) {
            leftPower = 1.0;
            return;
        } else if (pwr < -1.0) {
            leftPower = -1.0;
            return;
        }

        leftPower = pwr;
     }

     /**
      * Stop the right motor
      */
     public void stopRightMotor() {
         rightPower = 0.0;
         rtMotor.stopMotor();
         rmMotor.stopMotor();
         rbMotor.stopMotor();
     }

    /**
     * Stop the left motor
     */
    public void stopLeftMotor() {
        leftPower = 0.0;
        ltMotor.stopMotor();
        lmMotor.stopMotor();
        lbMotor.stopMotor();
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
        
        rtMotor.set(rightP);
        rmMotor.set(rightP);
        rbMotor.set(rightP);

        ltMotor.set(leftP);
        lmMotor.set(leftP);
        lbMotor.set(leftP);
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
        //diffDrive.arcadeDrive(fwd, rot);
    }

    /**
     * Controls the left and right sides of the drive directly with voltages
     * @param leftVolts the commanded left output
     * @param rightVolts the commanded right output
     */
   /*  public void tankDriveVolts(double leftVolts, double rightVolts) {
        leftMotors.setVoltage(leftVolts);
        rightMotors.setVoltage(-rightVolts);
    } */

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
    /* public void setMaxOutput(double maxOutput) {
        diffDrive.setMaxOutput(maxOutput);
    } */

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