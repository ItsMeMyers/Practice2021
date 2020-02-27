# 2020-InfiniteRecharge
This is code for the 2020 Infinite Recharge robot. This version of the robot has the LimeLight camera and API.

#############################################################
Below lines are gamepad layout as of 2/26/20
(NEVER HIT MODE BUTTON, LEAVE MODE LIGHT OFF)
(CONTROLLER IN X MODE)

- A Button: Fold turret down
- B Button: Unfold turret
- X Button: Intake into down position 
- Y Button: Intake into up position

- Right Bumper: Spin intake in
- Right Trigger: Spin intake out

- Left Bumper: Toggle shooter on/off
- Left Trigger: Run the feeders based on below rules
    - (No Balls in robot at all)
       Run upper tower, lower tower, funnel
    - (Ball at top TOF sensor BUT NOT lower TOF Sensor)
       Only run the lower tower and funnel
    - (Ball at top TOF sensor AND lower TOF Sensor AND we are NOT shooting)
       Stop all (lower, upper and funnel)
    - (Ball at top TOF sensor AND lower TOF Sensor AND we ARE shooting)
       Run upper tower, lower tower, funnel

- D-Pad Up: Release the climbing mechasnism
- D-Pad Left: Shooter pancake decrease angle
- D-Pad Right: Shooter pancake increase angle
- D-Pad Down: Emergency reverse all motors to evacuate all balls

- Left Joystick (PRESSED and MOVE DOWN AND {Climber is released}): Retract climber winch

- Right Joystick (PRESSED DOWN): :Lime Light auto target
- Right Joystick (NOT PRESSED DOWN, Left & Right): Control tower based on direction

 REQUIRED COMMANDS THAT AREN'T ASSIGNED YET
- Disengage climbing mechanism (maybe not necessary)
- Turn color wheel

###########################################################   

Laptop List

Probook 6550b (marked A/T 205896 on bottom)
    Screen Size : 1366 x 768
    Processor : I5 m460 @ 2.53 GHz
    Ram : 6 GB
    Hard Drive : 232 GB
    OS : Windows 10 pro 64 Bit
    Issues : Bad Wifi, 


Probook 6550b (marked 2 on bottom)
    Screen Size : 1366 x 768
    Processor : I5 m460 @ 2.53 GHz
    Ram : 6 GB
    Hard Drive : 120Gb SSD
    OS : Windows 10 pro 64 Bit
    Issues : none

Dell E6500
    Screen Size : 1920 x 1080
    Processor : Core 2 Duo T9550 @ 2.4 GHz
    Ram : 6 Gb
    Hard Drive : 120Gb SSD
    OS : Windows 10 Pro 64bit
    Issues : Bad keyboard (YHNUJM NOT WORKING)

Dell E6410
    Screen Size : 1440 x 900 
    Processor : I5 m460 @ 2.53 GHz
    Ram : 8 GB
    Hard Drive : 120 GB SSD 
    OS : Win 10 Pro education
    Issues : Poor Battery life

Mark's Dell 7440
    Screen Size : 1920 x 1080 
    Processor : I5 4300U @ 1.9 GHz
    Ram : 8 GB
    Hard Drive : 222 GB SSD 
    OS : Win 10 Pro education
    Issues : none

Driver Station HP 210 G1
    Screen Size : 1366 x 768
    Processor : I3 4010U @ 1.7 GHz
    Ram : 4 GB
    Hard Drive : 418 GB HDD
    OS : Win 10 Pro education
    Issues : Bad battery
