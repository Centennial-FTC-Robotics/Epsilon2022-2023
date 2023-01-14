package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystem;

public class Intake implements Subsystem {

    public Servo fourBar;
    public Servo grabber;
    public boolean grabberClosed = true;

    @Override
    public void initialize(LinearOpMode opMode, EpsilonRobot robot) {
        fourBar = opMode.hardwareMap.servo.get("fourBar");
        grabber = opMode.hardwareMap.servo.get("grabber");
    }

    public void suckStuff(boolean pressed){
        if(grabberClosed) {
            if (pressed) {
                fourBar.setPosition(0.4);
            } else {
                fourBar.setPosition(0.75);
            }
        }
    }

    public void grab(){
        grabber.setPosition(0.8);
        grabberClosed = true;
    }

    public void release(){
        grabber.setPosition(0.6);
        grabberClosed = false;
    }

    @Override
    public boolean active() {
        return true;
    }

    @Override
    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if(gamepad2.left_bumper){
            release();
        } else {
            grab();
            suckStuff(gamepad2.right_bumper);
        }
    }
}
