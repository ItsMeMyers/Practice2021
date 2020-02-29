package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class FeederRun extends CommandBase {
    private Feeder feeder;
    private Intake intake;
    private Shooter shooter;
    private final XboxController gamepad;
    /**
     * 1. This feeds the balls to the shooter mechanism. <br>
     * 2. This starts the motors on the feeder. <br>
     * 3. The feeder is the system that takes the balls up into the shooter.
     */
    public FeederRun(Feeder fd, Intake itk, Shooter str, XboxController gpd) {
        this.intake = itk;
        this.feeder = fd;
        this.shooter = str;
        this.gamepad = gpd;
        addRequirements(intake, feeder, shooter);
    }

    @Override
    public void execute() {
        //Look at the README.md for the rulesw that the below logic should match
        boolean lowerBallPresent = intake.getBallPresent();
        boolean upperBallPresent = feeder.getBallPresent();
        
        //No balls in tower at all
        if (!lowerBallPresent && !upperBallPresent) {
            feeder.run(false);
            intake.runFunnelIn(false);
            intake.runLowerTowerIn(false);
        //Ball at upper tower but not lower
        } else if (!lowerBallPresent && upperBallPresent) {
            intake.runFunnelIn(false);
            intake.runLowerTowerIn(false);
            feeder.stop();
        //We have balls at upper and lower
        } else if (lowerBallPresent && upperBallPresent) {
            //Shooter motors are at full speed 
            if (shooter.atSpeed()) {
                feeder.run(true);
                intake.runFunnelIn(true);
                intake.runLowerTowerIn(true);
            } else {
                feeder.stop();
                intake.stopFunnel();
                intake.stopLowerTower();
            }                
        }   
    }

    @Override
    public void end(boolean interrupted) {
        
        if (interrupted) {
            feeder.stop();
            intake.stopLowerTower();
            intake.stopFunnel();
        }
    }
}