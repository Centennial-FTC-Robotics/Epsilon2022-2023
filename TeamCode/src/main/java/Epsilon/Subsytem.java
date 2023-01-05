package Epsilon;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public interface Subsytem {

    void initialize(LinearOpMode opMode, EpsilonRobot robot);
    boolean active();

}
