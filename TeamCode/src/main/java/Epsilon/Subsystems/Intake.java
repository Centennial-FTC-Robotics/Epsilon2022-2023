package Epsilon.Subsystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystem;

public class Intake implements Subsystem {

    public PIDController controller;
    public static double p = 0.008, i = 0.07, d = 0.00068;
    public static double f = 0.34;

    public static final int targetOut = 285;
    public static final int targetIn = 30;

    //public static boolean positionOut;

    public final double ticks_in_degree = 537.7/360;

    public DcMotorEx fourBar;
    public Servo grabber;
    public boolean grabberClosed = true;

    @Override
    public void initialize(LinearOpMode opMode, EpsilonRobot robot) {
        controller = new PIDController(p, i, d);
        fourBar = opMode.hardwareMap.get(DcMotorEx.class, "fourBar");
        fourBar.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fourBar.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        grabber = opMode.hardwareMap.servo.get("grabber");
    }

    public void pivot(int target){
        controller.setPID(p, i, d);
        int armPos = fourBar.getCurrentPosition();

        /*
        if(!positionOut){
            positionOut = true;
            target = 285;
        } else {
            positionOut = false;
            target = 30;
        }
         */

        double pid = controller.calculate(armPos, target);
        double ff = Math.cos(Math.toRadians(target/ticks_in_degree)) * f;

        fourBar.setPower(pid + ff);
    }

    public void grab(){
        grabber.setPosition(0.35);
        grabberClosed = true;
    }

    public void release(){
        grabber.setPosition(0.25);
        grabberClosed = false;
    }

    @Override
    public boolean active() {
        return true;
    }

    @Override
    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if(gamepad2.left_bumper){
            pivot(targetOut);
            release();
        } else if(gamepad2.right_bumper){
            //pivot in
            grab();
            pivot(targetIn);
        }
    }
}
