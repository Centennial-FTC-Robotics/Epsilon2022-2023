package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystem;

public class Intake implements Subsystem {

    public DcMotor fourBar;
    public Servo claw;

    @Override
    public void initialize(LinearOpMode opMode, EpsilonRobot robot) {
        DcMotor fourBar = opMode.hardwareMap.dcMotor.get("fourBar");
        Servo claw = opMode.hardwareMap.servo.get("claw");

        fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void suckStuff(boolean state){
        if(state){
            fourBar.setPower(1);
        } else {
            fourBar.setPower(0);
        }
    }

    public void grab(){
        claw.setPosition(1);
    }

    public void release(){
        claw.setPosition(0);
    }

    @Override
    public boolean active() {
        return true;
    }

    @Override
    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {

    }
}
