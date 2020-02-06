package frc.robot.subsystems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DataRecorder extends SubsystemBase {

    File data1 = new File("/home/lvuser/data1.csv");
    File data2 = new File("/home/lvuser/data2.csv");
    PrintWriter pw1;
    PrintWriter pw2;
    Limelight limelight;
    double speed;
    double distance;
    int success;
    double dx;
    double dy;

    public DataRecorder(Limelight ll) {
        this.limelight = ll;
        if (!data1.exists()) {
            try {
                data1.createNewFile();
                data2.createNewFile();

                pw1 = new PrintWriter(data1);
                pw2 = new PrintWriter(data2);

                pw1.println("speed,distance,dx,dy");
                pw2.println("success");

                pw1.close();
                pw2.close();

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

            FileWriter fW1 = new FileWriter(data1, true);
            FileWriter fW2 = new FileWriter(data2, true);
            pw1 = new PrintWriter(fW1);
            pw2 = new PrintWriter(fW2);

            pw1.print("\"");
            pw1.print(speed);
            pw1.print("\", \"");
            pw1.print(distance);
            pw1.print("\",\"");
            pw1.print(dx);
            pw1.print("\", \"");
            pw1.print(dy);
            pw1.println("\"");
            pw1.close();
            pw2.print("\"");
            pw2.print(success);
            pw2.println("\"");
            pw2.close();
            
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

}