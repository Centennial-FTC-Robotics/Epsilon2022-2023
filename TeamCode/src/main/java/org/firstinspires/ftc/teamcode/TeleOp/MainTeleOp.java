package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Epsilon.EpsilonRobot;

@TeleOp
public class MainTeleOp extends LinearOpMode {

    //public DcMotorEx fourBar;
    //public Servo grabber;

    @Override
    public void runOpMode() throws InterruptedException {
        EpsilonRobot robot = new EpsilonRobot();
        robot.initialize(this);

        waitForStart();

        while(opModeIsActive()){
            robot.teleOpUpdate(gamepad1, gamepad2);
            telemetry.update();
        }
    }
}
