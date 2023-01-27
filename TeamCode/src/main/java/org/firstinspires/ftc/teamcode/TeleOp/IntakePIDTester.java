package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class IntakePIDTester extends LinearOpMode {

    public PIDController controller;
    public static double p = 0.008, i = 0.07, d = 0.00068;
    public static double f = 0.34;

    public static int target = 0;

    public static double servoPos = 0.3;

    public final double ticks_in_degree = 537.7/360;

    private DcMotorEx fourBar;
    private Servo grabber;

    @Override
    public void runOpMode() throws InterruptedException {
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        fourBar = hardwareMap.get(DcMotorEx.class, "fourBar");
        fourBar.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fourBar.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        grabber = hardwareMap.get(Servo.class, "grabber");


        waitForStart();

        while(opModeIsActive()) {
            controller.setPID(p, i, d);
            int armPos = fourBar.getCurrentPosition();
            double pid = controller.calculate(armPos, target);
            double ff = Math.cos(Math.toRadians(target/ticks_in_degree)) * f;

            double power = pid + ff;

            grabber.setPosition(servoPos);

            fourBar.setPower(power);
            telemetry.addData("pos", armPos);
            telemetry.addData("target", target);
            telemetry.update();

        }

    }



}
