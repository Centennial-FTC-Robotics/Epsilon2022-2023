package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.Subsystems.EpsilonRobot;

@TeleOp
public class MainTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        EpsilonRobot.initialize(this);

        waitForStart();

        while(opModeIsActive()){
            //driving code
        }
    }
}
