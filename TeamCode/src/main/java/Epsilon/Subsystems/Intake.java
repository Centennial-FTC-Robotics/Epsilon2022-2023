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

    @Override
    public void initialize(LinearOpMode opMode, EpsilonRobot robot) {
        fourBar = opMode.hardwareMap.servo.get("fourBar");
        grabber = opMode.hardwareMap.servo.get("grabber");

    }

    public void suckStuff(boolean state){
        if(state){
            fourBar.setPosition(0.5);
        } else {
            fourBar.setPosition(0.25);
        }
    }

    public void grab(){
        grabber.setPosition(0.5);
    }

    public void release(){
        grabber.setPosition(0.25);
    }

    @Override
    public boolean active() {
        return true;
    }

    @Override
    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if(gamepad2.left_bumper){
            grab();
        } else {
            release();
        }
        if(gamepad2.right_bumper) {
            suckStuff(true);
        } else {
            suckStuff(false);
        }
    }
}
