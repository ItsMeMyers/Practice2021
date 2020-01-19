package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

    private WPI_TalonFX r1Motor;
    private WPI_TalonFX r2Motor;
    private WPI_TalonFX r3Motor;
    private WPI_TalonFX l1Motor;
    private WPI_TalonFX l2Motor;
    private WPI_TalonFX l3Motor;

    private double rightPower = 0.0;
    private double leftPower = 0.0;

    private boolean invertRight = false;
    private boolean invertLeft = false;

    private boolean halfSpeed = false;

    public Drivetrain() {
        r1Motor = new WPI_TalonFX(Constants.r1Motor);
        r2Motor = new WPI_TalonFX(Constants.r2Motor);
        r3Motor = new WPI_TalonFX(Constants.r3Motor);
        l1Motor = new WPI_TalonFX(Constants.l1Motor);
        l2Motor = new WPI_TalonFX(Constants.l2Motor);
        l3Motor = new WPI_TalonFX(Constants.l3Motor);

        r1Motor.setNeutralMode(NeutralMode.Coast);
        r2Motor.setNeutralMode(NeutralMode.Coast);
        r3Motor.setNeutralMode(NeutralMode.Coast);
        l1Motor.setNeutralMode(NeutralMode.Coast);
        l2Motor.setNeutralMode(NeutralMode.Coast);
        l3Motor.setNeutralMode(NeutralMode.Coast);
    }

    public double getLeftPower() {
        return leftPower;
    }

    public double getRightPower() {
        return rightPower;
    }

    public void setRightPower(double rightPwr) {
        
        if (rightPwr > 1.0) {
            rightPwr = 1.0;
        } else if (rightPwr < -1.0) {
            rightPwr = -1.0;
        }

        this.rightPower = rightPwr;
    }

    public void setLeftPower(double leftPwr) {
        
        if (leftPwr > 1.0) {
            leftPwr = 1.0;
        } else if (leftPwr < -1.0) {
            leftPwr = -1.0;
        }

        this.leftPower = leftPwr;
    }

    public void drive() {

        //Try out parabolic power scalar
        if (invertRight) {
            rightPower *= Constants.INVERT_MOTOR;
        }
        
        if (invertLeft) {
            leftPower *= Constants.INVERT_MOTOR;
        }

        if (halfSpeed) {
            rightPower *= .5;
            leftPower *= .5;
        }

        r1Motor.set(rightPower);
        r2Motor.set(rightPower);
        r3Motor.set(rightPower);
        l1Motor.set(leftPower);
        l2Motor.set(leftPower);
        l3Motor.set(leftPower);
    }

    /**public void drive(double rightP, double leftP) {
        
        if (invertRight) {
            rightP *= Constants.INVERT_MOTOR;
        }
        
        if (invertLeft) {
            leftP *= Constants.INVERT_MOTOR;
        }
        
        r1Motor.set(rightP);
        r2Motor.set(rightP);
        r3Motor.set(rightP);
        l1Motor.set(leftP);
        l2Motor.set(leftP);
        l3Motor.set(leftP);
    }**/

    public boolean getHalfSpeed() {
        return halfSpeed;
    }

    public void toggleHalfSpeed() {

        halfSpeed = !halfSpeed;
    }

    public void stop() {
        
        rightPower = 0.0;
        leftPower = 0.0;
        r1Motor.stopMotor();
        r2Motor.stopMotor();
        r3Motor.stopMotor();
        l1Motor.stopMotor();
        l2Motor.stopMotor();
        l3Motor.stopMotor();
    }    
}