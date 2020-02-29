/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  
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

  private Command m_autonomousCommand;
  private Limelight limelight = new Limelight();
  private RobotContainer robotContainer;

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    limelight.setLED(defaultLED[0]);
    limelight.setCAM(defaultCAM[0]);
  }

  @Override
  public void robotPeriodic() {
    
  }

  @Override
  public void disabledInit() {
    limelight.setLED(defaultLED[1]);
    limelight.setCAM(defaultCAM[1]);
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    limelight.setLED(defaultLED[2]);
    limelight.setCAM(defaultCAM[2]);
    //m_autonomousCommand = robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    limelight.setLED(defaultLED[3]);
    limelight.setCAM(defaultCAM[3]);
    
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    //This should fire off commands to the robot based on the user input to controller?
    //Every x ms
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    limelight.setLED(defaultLED[4]);
    limelight.setCAM(defaultCAM[4]);
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
