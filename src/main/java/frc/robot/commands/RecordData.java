package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DataRecorder;

public class RecordData extends CommandBase {

    DataRecorder dataRecorder;
    
    private boolean success;

    public RecordData(DataRecorder dR, boolean s) {
        this.dataRecorder = dR;
        this.success = s;
    }

    @Override
    public void execute() {
        dataRecorder.setSuccess(success);
        dataRecorder.writeTemp();
    }
}