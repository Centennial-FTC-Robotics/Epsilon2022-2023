package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import static Epsilon.Subsystems.EpsilonRobot.drivetrain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import Epsilon.Subsystems.EpsilonRobot;

@TeleOp
public class MainTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        EpsilonRobot.initialize(this);

        waitForStart();

        while(opModeIsActive()){

            double yPower = this.gamepad1.left_stick_y;
            double xPower = this.gamepad1.left_stick_x;
            double turn = this.gamepad1.right_stick_x;

            drivetrain.frontLeft.setPower(yPower - xPower + turn);
            drivetrain.backLeft.setPower(yPower + xPower + turn);
            drivetrain.frontRight.setPower(yPower + xPower  - turn);
            drivetrain.backRight.setPower(yPower - xPower - turn);

        }
    }
}
