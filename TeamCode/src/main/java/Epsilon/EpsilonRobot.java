package Epsilon;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.Intake;
import Epsilon.Subsystems.Outtake;

public class EpsilonRobot {

    public Drivetrain drivetrain = new Drivetrain();
    public Intake intake = new Intake();
    public Outtake outtake = new Outtake();
    public Subsystem[] subsystems = new Subsystem[] {
      drivetrain, intake, outtake
    };

    public void initialize(LinearOpMode opMode){
        for(Subsystem subsystem : subsystems) {
            if(subsystem.active()) {
                subsystem.initialize(opMode, this);
            }
        }
    }
}
