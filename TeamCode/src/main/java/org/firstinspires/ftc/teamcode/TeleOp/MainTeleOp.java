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

    public DcMotor outtakePulley;
    public Servo platform;
    //public DcMotorEx fourBar;
    //public Servo grabber;

    @Override
    public void runOpMode() {
        EpsilonRobot robot = new EpsilonRobot();
        robot.initialize(this);
        outtakePulley = hardwareMap.dcMotor.get("outtakePulley");
        platform = hardwareMap.servo.get("platform");
       // fourBar = hardwareMap.get(DcMotorEx.class, "fourBar");
        //grabber = hardwareMap.servo.get("grabber");

        outtakePulley.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       // fourBar.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        while(opModeIsActive()){
            robot.teleOpUpdate(gamepad1, gamepad2);


           // fourBar.setPower(0.65*gamepad2.right_stick_y);
            /*
            if(gamepad2.left_bumper){
                grabber.setPosition(0.5);
            } else {
                grabber.setPosition(0);
            }
            */
           // outtakePulley.setPower(0.3*gamepad2.left_stick_y);
            if(gamepad2.a){
                platform.setPosition(0.5);
            } else {
                platform.setPosition(0);
            }



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
