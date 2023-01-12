package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.EpsilonRobot;

@TeleOp
public class MainTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        EpsilonRobot robot = new EpsilonRobot();
        robot.initialize(this);

        waitForStart();

        while(opModeIsActive()){
            robot.drivetrain.teleOpUpdate(gamepad1, gamepad2);
        }
    }
}
