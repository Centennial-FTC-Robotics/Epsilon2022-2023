package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
            robot.intake.teleOpUpdate(gamepad1, gamepad2);

        //    robot.outtake.leftPulley.setPower(0.2*gamepad2.left_stick_y);
        //    robot.outtake.rightPulley.setPower(0.2*gamepad2.left_stick_y);

        //    telemetry.addData("leftPulley encoder value: ", robot.outtake.leftPulley.getCurrentPosition());
        //    telemetry.addData("rightPulley encoder value: ", robot.outtake.rightPulley.getCurrentPosition());
        //    telemetry.update();

        }
    }
}
