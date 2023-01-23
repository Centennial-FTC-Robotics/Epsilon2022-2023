package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystems.Drivetrain;


@Autonomous
public class AutoMain extends LinearOpMode {
    @Override
    public void runOpMode() {
        EpsilonRobot robot = new EpsilonRobot();
        robot.initialize(this);

        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("April Tag: ", robot.camera.scanParking());
            telemetry.update();
        }


    }
}
