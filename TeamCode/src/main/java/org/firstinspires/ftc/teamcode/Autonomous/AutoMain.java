package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystems.Drivetrain;


@Autonomous
public class AutoMain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        EpsilonRobot robot = new EpsilonRobot();
        robot.initialize(this);

        waitForStart();

       // while(opModeIsActive()) {

            robot.drivetrain.park();
            telemetry.addData("April Tag: ", robot.camera.scanParking());
            telemetry.update();
       // }


    }
}
