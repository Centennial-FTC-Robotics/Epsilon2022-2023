package Epsilon;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.Subsystems.Drivetrain;

public class EpsilonRobot {

    public Drivetrain drivetrain = new Drivetrain();
    public Subsystem[] subsystems = new Subsystem[] {
      drivetrain
    };

    public void initialize(LinearOpMode opMode){
        for(Subsystem subsystem : subsystems) {
            if(subsystem.active()) {
                subsystem.initialize(opMode, this);
            }
        }
    }
}
