package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp
public class IntakePIDTester extends LinearOpMode {

    public PIDController controller;
    public static double p = 0.005, i = 0.006, d = 0.0002;
    public static double f = 0;

    public static int target = 0;

    public final double ticks_in_degree = 537.7/360;

    private DcMotorEx fourBar;

    @Override
    public void runOpMode() throws InterruptedException {
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        fourBar  = hardwareMap.get(DcMotorEx.class, "fourBar");

        waitForStart();

        while(opModeIsActive()) {
            controller.setPID(p, i, d);
            int armPos = fourBar.getCurrentPosition();
            double pid = controller.calculate(armPos, target);
            double ff = Math.cos(Math.toRadians(target/ticks_in_degree)) * f;

            double power = pid + ff;

            fourBar.setPower(power);
            telemetry.addData("pos", armPos);
            telemetry.addData("target", target);
            telemetry.update();

        }

    }



}
