package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystem;

public class Outtake implements Subsystem {

    public DcMotor leftPulley;
    public DcMotor rightPulley;
    public Servo leftServo;
    public Servo rightServo;

    public boolean active() {
        return true;
    }

    public void initialize(LinearOpMode opMode, EpsilonRobot robot) {
        leftPulley = opMode.hardwareMap.dcMotor.get("leftPulley");
        rightPulley = opMode.hardwareMap.dcMotor.get("rightPulley");

        leftServo = opMode.hardwareMap.servo.get("leftOuttakeServo");
        rightServo = opMode.hardwareMap.servo.get("rightOuttakeServo");

        leftPulley.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightPulley.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public double PID(double targetPos, DcMotor motor) {
        double kP = 0.01;
        double power;
        double error = targetPos - motor.getCurrentPosition();
        power = error * kP;
        return power;
    }

    // test commit






}
