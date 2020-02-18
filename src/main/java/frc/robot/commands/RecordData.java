package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DataRecorder;

public class RecordData extends CommandBase {

    private final DataRecorder dataRecorder;
    
    private int success;

    public RecordData(DataRecorder dR, int s) {
        this.dataRecorder = dR;
        this.success = s;
        addRequirements(dataRecorder);
    }

    @Override
    public void execute() {
        dataRecorder.setSuccess(success);
        dataRecorder.writeTemp();
    }
}