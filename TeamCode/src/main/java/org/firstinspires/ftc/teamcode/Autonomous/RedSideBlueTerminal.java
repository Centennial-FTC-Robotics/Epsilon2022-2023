package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystems.Drivetrain;

@Autonomous
public class RedSideBlueTerminal extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        EpsilonRobot robot = new EpsilonRobot();
        robot.initialize(this);

        waitForStart();

        robot.drivetrain.Move(0.5, 20, Drivetrain.MoveType.DRIVE, this);
    }
}
