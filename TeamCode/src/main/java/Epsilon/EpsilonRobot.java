package Epsilon;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.Subsystems.Drivetrain;

public class EpsilonRobot {

    public Drivetrain drivetrain = new Drivetrain();
    public Subsytem[] subsystems = new Subsytem[] {
      drivetrain
    };

    public void initialize(LinearOpMode opMode){
        for(Subsytem subsystem : subsystems) {
            subsystem.initialize(opMode, this);
        }
    }
}
