package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;

public class MoveTurret extends CommandBase {
    
    // If the speed of the motor is less than this threshold, we'll just set it to zero
    public static final double percentToTurnMotorWhenCommandedByButton = .05;

    private final Turret turret;
    private final Joystick gamepad;

    /**
     * 1. Comply with limit switchs <br>
     * 3. Takes power value from the left joystick on the game pad <br>
     * 4. Comply with minimum power threshold <br>
     * 5. Set the turret's speed
     * 
     * @param trrt Turret subsystem
     * @param gmpd XboxController instance
     */
    public MoveTurret(Turret trrt, Joystick gmpd) {
        this.turret = trrt;
        this.gamepad = gmpd;
        addRequirements(turret);
    }
    
    @Override
    public void execute() {

        //If right stick is NOT pressed down AND joystick x axis is moved (disregarding nominal movement)
        //Is .02 a good threshold?
        
    }

    /**
     * Stops the turret when the command ends
     */
    @Override
    public void end(boolean interrupted) {
        turret.stopTurret();
    }
}