package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class FeederRun extends CommandBase {
    private Feeder feeder;
    private Intake intake;
    private Shooter shooter;
    /**
     * 1. This feeds the balls to the shooter mechanism. <br>
     * 2. This starts the motors on the feeder. <br>
     * 3. The feeder is the system that takes the balls up into the shooter.
     */
    public FeederRun(Feeder fd, Intake itk, Shooter str, Joystick gpd) {
        this.intake = itk;
        this.feeder = fd;
        this.shooter = str;
        addRequirements(intake, feeder, shooter);
    }

    // check sensors to see if ball is present at locations.
    // if present then run all but that locations motors
    @Override
    public void execute() {
        //Look at the README.md for the rulesw that the below logic should match
        boolean lowerBallPresent = intake.getBallPresent();
        boolean upperBallPresent = feeder.getBallPresent();

        //boolean lowerBallPresent = false;
        //boolean upperBallPresent = false;
        //No balls in tower at all

    }

    public void feed(){
        execute();
    }
}