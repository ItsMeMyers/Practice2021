/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveTele;
import frc.robot.commands.FeederRun;
import frc.robot.commands.MoveTurret;
import frc.robot.commands.SimpleAuto;
import frc.robot.commands.SimpleAuto2;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  
  private DriveTele drivetele = new DriveTele(RobotContainer.drivetrain, RobotContainer.rightStick, RobotContainer.leftStick);
  private MoveTurret moveTurret = new MoveTurret(RobotContainer.turret, RobotContainer.gamepad);
  // Default Limelight modes for when the different robot states initialize
  // 0: Initial State (RobotInit)
  // 1: Disabled
  // 2: Autonomous
  // 3: TeleOp
  // 4: Test
  private final Limelight.LED[] defaultLED = {
    Limelight.LED.OFF,
    Limelight.LED.OFF,
    Limelight.LED.ON,
    Limelight.LED.OFF,
    Limelight.LED.ON
  };
  
  private final Limelight.CAM[] defaultCAM = {
    Limelight.CAM.DRIVER,
    Limelight.CAM.DRIVER,
    Limelight.CAM.VISION,
    Limelight.CAM.DRIVER,
    Limelight.CAM.VISION
  };

  private SendableChooser<String> autoChooser;
  private Limelight limelight;
  private RobotContainer m_robotContainer;
  private Joystick gamepad;
  private Intake intake;
  private Shooter shooter;
  private Drivetrain drivetrain;
  private Feeder feeder;
  private Climber climber;
  private Turret turret;
  private ColorWheel colorWheel;
  private CommandBase desiredAutoCommand;

  @Override
  public void robotInit() {
    limelight = m_robotContainer.limelight;
    limelight.setLED(1);
    limelight.setCAM(0);
    m_robotContainer = new RobotContainer();
    gamepad = m_robotContainer.gamepad;
    intake = m_robotContainer.intake;
    shooter = m_robotContainer.shooter;
    drivetrain = m_robotContainer.drivetrain;
    feeder = m_robotContainer.feeder;
    climber = m_robotContainer.climber;
    turret = m_robotContainer.turret;
    colorWheel = m_robotContainer.colorwheel;
    autoChooser = new SendableChooser<String>();
    autoChooser.setDefaultOption("Shoot & Scoot", Constants.SHOOT_SCOOT);
    autoChooser.setName("Autonomous Command");
    autoChooser.addOption("Trench Run", Constants.TRENCH_RUN);

    SmartDashboard.putData(autoChooser);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    limelight.setLED(1);
    limelight.setCAM(0);
  }

  @Override
  public void disabledPeriodic() {
    limelight.setLED(1);
    climber.stop();
    feeder.stop();
    intake.stopFunnel();
    intake.stopIntake();
    intake.stopLowerTower();
    shooter.stopShooter();
    intake.intakeUp();
    climber.withdraw();

        // Display values to driver station
        readTalonsAndShowValues();
  }

  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().cancelAll();
    limelight.setLED(3);
    limelight.setCAM(0);
    
    shooter.setSpeed(5000);
    turret.zeroTurret();
    desiredAutoCommand = getDesiredAutoCommand();
    if (desiredAutoCommand != null) {
      desiredAutoCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  public CommandBase getDesiredAutoCommand() {
    String selected = autoChooser.getSelected();
    double speed = -.3;
    double driveTime = 0.5;
    double shootTime = 3.0;
    if (selected.equals(Constants.SHOOT_SCOOT)) {
      return new SimpleAuto(shooter, drivetrain, speed, shootTime, driveTime, feeder, intake);
    } else if (selected.equals(Constants.TRENCH_RUN)) {
      return new SimpleAuto2(shooter, turret, drivetrain, speed, driveTime, shootTime, feeder, intake);
    }

    return null;
  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
    limelight.setLED(3);
    limelight.setCAM(0);
    
    drivetele.schedule();
    moveTurret.schedule();

  }

  @Override
  public void teleopPeriodic() {
    //This should fire off commands to the robot based on the user input to controller?
    //Every 20 ms
    CommandScheduler.getInstance().run();
    if(gamepad.getRawButton(Constants.Right_Trigger_Button) && !gamepad.getRawButton(Constants.Right_Bumper_Button)){
      intake.runIntakeIn(true);
    }else if(gamepad.getRawButton(Constants.Right_Bumper_Button) &&! gamepad.getRawButton(Constants.Right_Trigger_Button) ){
      intake.runIntakeOut(true);
    }else{
      intake.stopIntake();
    }

    if(gamepad.getRawButton(Constants.Left_Bumper_Button)){
      // if(shooter.isRunning())
      // {
      //     shooter.stopShooter();
      //     shooter.setRunning(false);
      // }else{
      //     shooter.setRunning(true);
      //     shooter.getToSpeed();
      // }
      shooter.setRunning(true);
      shooter.getToSpeed();
    } else {
      shooter.stopShooter();
      shooter.setRunning(false);
    }

    if (gamepad.getRawButton(Constants.Right_Joystick_Pressed)) {
      turret.targetEntity();
    }

    if(gamepad.getRawButton(Constants.X_Button)){
      intake.intakeDown();
    }
    if(gamepad.getRawButton(Constants.Y_Button)){
      intake.intakeUp();
    }
    if(gamepad.getRawButton(Constants.Left_Trigger_Button) || (gamepad.getRawButton(Constants.Right_Bumper_Button) && !gamepad.getRawButton(Constants.Right_Trigger_Button))){
      FeederRun run = new FeederRun(feeder, intake, shooter, gamepad);
      run.feed();
    }else{
      feeder.stop();
      intake.stopFunnel();
      intake.stopLowerTower();
    }
    if(gamepad.getPOV() == Constants.D_Pad_Up){
      if (!climber.getDeployed()) {
          climber.deploy();
      }
    }
    if(gamepad.getPOV() == Constants.D_Pad_Down){
      intake.runAllOut();
      shooter.reverseShooters();
      feeder.reverse();
    }
    if(gamepad.getRawButton(Constants.Left_Joystick_Pressed)){
      
      double speed = gamepad.getRawAxis(Joystick.AxisType.kY.value);
      if(speed < 0.0)
          speed = 0.0;
      climber.climb(speed);
    } else {
      climber.climb(0.0);
    }

    //Below would be the check to see if the user is saying rotate the color wheel
    if (false) {
      colorWheel.spin();
    }

    if(gamepad.getPOV() == Constants.D_Pad_Left){
      double tempSpeed = shooter.getSpeed() - 100;
      if (tempSpeed < 0) {
        tempSpeed = 0;
      }
      shooter.setSpeed(tempSpeed);
      if(shooter.isRunning()) {
        shooter.getToSpeed();
      }
    } else if(gamepad.getPOV() == Constants.D_Pad_Right){
      double tempSpeed = shooter.getSpeed() +100;
      if (tempSpeed > 6300) {
        tempSpeed = 6300;
      }
      shooter.setSpeed(tempSpeed);
      if(shooter.isRunning()) {
        shooter.getToSpeed();
      }
    }

    // Display values to driver station
    readTalonsAndShowValues();
  }

  @Override
  public void testInit() {
    limelight.setLED(3);
    limelight.setCAM(0);
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }

  public void readTalonsAndShowValues() {
    // Display shooter motor speeds
    SmartDashboard.putNumber("shooter1RPM:", shooter.getLeftRPM());
    SmartDashboard.putNumber("shooter2RPM:", shooter.getRightRPM());
    SmartDashboard.putNumber("TurretPos", turret.getPosition());
    SmartDashboard.putNumber("Shooter Target Speed", shooter.getSpeed());
  }
}
