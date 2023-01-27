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
public class OuttakePIDTester extends LinearOpMode {

    public PIDController controller;
    public static double p = 0, i = 0, d = 0;
    public static double f = 0;

    public static int target = 0;

    public static double servoPos = 0;

    private DcMotorEx outtakePulley;
    private ServoEx platform;

    @Override
    public void runOpMode() throws InterruptedException {
//        controller = new PIDController(p, i, d);
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//        outtakePulley  = hardwareMap.get(DcMotorEx.class, "outtakePulley");

//        outtakePulley.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        outtakePulley.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Servo servo = hardwareMap.servo.get("platform");

        waitForStart();

        while(opModeIsActive()) {
            servo.setPosition(servoPos);
//            controller.setPID(p, i, d);
//            int armPos = outtakePulley.getCurrentPosition();
//            double pid = controller.calculate(armPos, target);
//
//            double power = pid;
//
//            outtakePulley.setPower(power);
//            telemetry.addData("pos", armPos);
//            telemetry.addData("target", target);
//            telemetry.update();

        }
    }
}
