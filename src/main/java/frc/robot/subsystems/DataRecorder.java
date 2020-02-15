package frc.robot.subsystems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DataRecorder extends SubsystemBase {

    File data1 = new File("/home/lvuser/data1.csv");
    PrintWriter pw;
    double speed;
    double distance;
    int success;
    double dx;
    double dy;
    String tempStore = "success,distance,dx,dy,speed";
    String midSep = "\",\"";

    public DataRecorder() {
        if (!data1.exists()) {
            try {
                data1.createNewFile();

                pw = new PrintWriter(data1);

                pw.println("success,distance,dx,dy,speed");

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setSpeed(double spd) {
        this.speed = spd;
    }

    public void setDistance(double dist) {
        this.distance = dist;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setX(double dx) {
        this.dx = dx;
    }

    public void setY(double dy) {
        this.dy = dy;
    }

    public void writeTemp() {

        tempStore.concat(
            "\"" 
            + Integer.toString(success) 
            + midSep
            + Double.toString(distance) 
            + midSep
            + Double.toString(dx) 
            + midSep
            + Double.toString(dy) 
            + midSep
            + Double.toString(speed) 
            + "\"\n");
    }

    public void finalWrite() {
        FileWriter fw;
        try {
            fw = new FileWriter(data1, true);

            pw.print(fw);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}