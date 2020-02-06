package frc.robot.subsystems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DataRecorder extends SubsystemBase {

    File data = new File("/home/lvuser/data.csv");
    PrintWriter pw;
    Limelight limelight;
    double speed;
    double distance;
    int success;
    double dx;
    double dy;

    public DataRecorder(Limelight ll) {
        this.limelight = ll;
        if (!data.exists()) {
            try {
                data.createNewFile();

                pw = new PrintWriter(data);

                pw.println("speed,distance,dx,dy,success");

                pw.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
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

    public void dx() {
        double dx = limelight.x();
        this.dx = dx;
    }

    public void dy() {
        double dy = limelight.y();
        this.dy = dy;
    }

    public void writeData() {

        try {

            FileWriter fW = new FileWriter(data, true);
            pw = new PrintWriter(fW);

            pw.print("\"");
            pw.print(speed);
            pw.print("\", \"");
            pw.print(distance);
            pw.print("\",\"");
            pw.print(dx);
            pw.print("\", \"");
            pw.print(dy);
            pw.print("\", \"");
            pw.print(success);
            pw.println("\"");
            pw.close();
            
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

}