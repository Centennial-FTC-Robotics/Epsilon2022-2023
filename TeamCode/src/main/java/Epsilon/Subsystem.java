package Epsilon;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public interface Subsystem {

    void initialize(LinearOpMode opMode, EpsilonRobot robot);
    boolean active();

}
