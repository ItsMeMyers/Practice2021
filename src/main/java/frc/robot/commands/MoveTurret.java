package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class MoveTurret extends CommandBase {
    
    // If the speed of the motor is less than this threshold, we'll just set it to zero
    private double power = 0.0;
    public static final double percentToTurnMotorWhenCommandedByButton = .05;

    private final Turret turret;
    private boolean rotateTurretLeftPressed = false;
    private boolean rotateTurretRightPressed = false;
    private final XboxController gamepad;

    /**
     * 1. Comply with limit switchs <br>
     * 3. Takes power value from the left joystick on the game pad <br>
     * 4. Comply with minimum power threshold <br>
     * 5. Set the turret's speed
     * 
     * @param trrt Turret subsystem
     * @param gmpd XboxController instance
     */
    public MoveTurret(Turret trrt, XboxController gmpd) {
        this.turret = trrt;
        this.gamepad = gmpd;
        addRequirements(turret);
    }
    
    @Override
    public void execute() {
        rotateTurretLeftPressed = gamepad.getRawButton(7);
        rotateTurretRightPressed = gamepad.getRawButton(8);

        // The setSpinPower function automatically takes care of the hard stop
        // The user can override what the limelight says based on the buttons pressed
        if (rotateTurretLeftPressed && !rotateTurretRightPressed) {
            power = -percentToTurnMotorWhenCommandedByButton;
        } else if (!rotateTurretLeftPressed && rotateTurretRightPressed){
            power = percentToTurnMotorWhenCommandedByButton;
        } else if (false) {
            //Limelight stuff here
        } else {
            power = 0.0;
        }

        turret.setSpinPower(power);
    }

    /**
     * Stops the turret when the command ends
     */
    @Override
    public void end(boolean interrupted) {
        turret.stopTurret();
    }
}