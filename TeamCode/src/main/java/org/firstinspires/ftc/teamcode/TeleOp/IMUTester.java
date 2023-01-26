package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class IMUTester extends LinearOpMode {

    public RevIMU imu;

    @Override
    public void runOpMode() throws InterruptedException {

       MecanumDrive drive = new MecanumDrive(
               new Motor(hardwareMap, "frontLeft", 537.6, 340),
               new Motor(hardwareMap, "frontRight", 537.6, 340),
               new Motor(hardwareMap, "backLeft", 537.6, 340),
               new Motor(hardwareMap, "backRight", 537.6, 340)
       );

        imu = new RevIMU(hardwareMap, "imu");
        imu.init();
        imu.reset();

        GamepadEx driverOp = new GamepadEx(gamepad1);

        waitForStart();

        while(opModeIsActive()) {

            drive.driveFieldCentric(
                    driverOp.getLeftX(),
                    driverOp.getLeftY(),
                    driverOp.getRightX(),
                    imu.getRotation2d().getDegrees(),
                    false
            );

            telemetry.addData("heading", imu.getHeading());
            telemetry.update();

        }

    }



}
