package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Epsilon.EpsilonRobot;

@TeleOp
public class MainTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        EpsilonRobot robot = new EpsilonRobot();
        robot.initialize(this);

        waitForStart();

        while(opModeIsActive()){
            robot.teleOpUpdate(gamepad1, gamepad2);

            /*
            if(gamepad2.right_bumper){
                robot.intake.fourBar.setPosition(0.25);
            } else {
                robot.intake.fourBar.setPosition(0.5);
            }
            */

           // robot.outtake.leftPulley.setPower(-0.2*gamepad2.left_stick_y);
           // robot.outtake.rightPulley.setPower(0.2*gamepad2.left_stick_y);

//            telemetry.addData("leftPulley encoder value: ", robot.outtake.leftPulley.getCurrentPosition());
//            telemetry.addData("rightPulley encoder value: ", robot.outtake.rightPulley.getCurrentPosition());

           // telemetry.addData("grabber stage: ", robot.intake.grabberClosed);
           // telemetry.update();

        }
    }
}
