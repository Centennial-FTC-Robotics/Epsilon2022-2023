package Epsilon;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public interface Subsystem {

    void initialize(LinearOpMode opMode, EpsilonRobot robot);
    boolean active();
    void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) throws InterruptedException;


}
