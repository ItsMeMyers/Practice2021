package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DataRecorder;

public class RecordData extends CommandBase {

    DataRecorder dataRecorder;
    
    private int success;

    public RecordData(DataRecorder dR, int i) {
        this.dataRecorder = dR;
        this.success = i;
    }

    @Override
    public void execute() {
        
        dataRecorder.setSuccess(success);
        dataRecorder.writeTemp();
    }

}