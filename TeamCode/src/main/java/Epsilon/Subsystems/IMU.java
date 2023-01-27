package Epsilon.Subsystems;

import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystem;

public class IMU implements Subsystem {

    public RevIMU imu;

    @Override
    public void initialize(LinearOpMode opMode, EpsilonRobot robot) {
        imu = new RevIMU(opMode.hardwareMap, "imu");
        imu.init();
        imu.reset();
    }

    public RevIMU getRevIMU() {
        return imu;
    }



    @Override
    public boolean active() {
        return true;
    }

    @Override
    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {}
}
