package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystem;

public class Drivetrain implements Subsystem {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public boolean active() {
        return false;
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        double yPower = 0.7*gamepad1.left_stick_y;
        double xPower = 0.7*gamepad1.left_stick_x;
        double turn = -0.4*gamepad1.right_stick_x;

        frontLeft.setPower(yPower - xPower + turn);
        backLeft.setPower(yPower + xPower + turn);
        frontRight.setPower(yPower + xPower  - turn);
        backRight.setPower(yPower - xPower - turn);
    }

    public void park() throws InterruptedException {
        double yPower = .2;
        double xPower = 0;
        double turn = 0;

        frontLeft.setPower(yPower - xPower + turn);
        backLeft.setPower(yPower + xPower + turn);
        frontRight.setPower(yPower + xPower  - turn);
        backRight.setPower(yPower - xPower - turn);
        Thread.sleep(12000);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

    }

    public enum MoveType {
        DRIVE,
        STRAFE,
        TURN
    }

    public void TargetPos(double EncoderCounts, MoveType Type) {
        switch (Type) {
            case DRIVE:
                frontLeft.setTargetPosition((int) EncoderCounts);
                frontRight.setTargetPosition((int) EncoderCounts);
                backLeft.setTargetPosition((int) EncoderCounts);
                backRight.setTargetPosition((int) EncoderCounts);
                break;
            case STRAFE:
                frontLeft.setTargetPosition((int) -EncoderCounts);
                frontRight.setTargetPosition((int) EncoderCounts);
                backLeft.setTargetPosition((int) EncoderCounts);
                backRight.setTargetPosition((int) -EncoderCounts);
                break;
            case TURN:
                frontLeft.setTargetPosition((int) -EncoderCounts);
                frontRight.setTargetPosition((int) EncoderCounts);
                backLeft.setTargetPosition((int) -EncoderCounts);
                backRight.setTargetPosition((int) EncoderCounts);
                break;
        }
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public double INtoEC(double inches) {
        //Inches to Encoder Counts Stuff
        //(8192 ticks / 1 rev) * (1 rev / 1.96pi )
        double COUNTS_PER_INCH = 8192/(Math.PI*1.96);
        double EncoderCounts = inches * COUNTS_PER_INCH;
        return EncoderCounts;
    }

    public void Move(double power, int inches, MoveType Type, LinearOpMode opMode) {
        double EncoderCounts = INtoEC(inches);
        TargetPos(EncoderCounts, Type);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        while (frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy()
                || backRight.isBusy() && opMode.opModeIsActive()) {

        }
    }

    public void initialize(LinearOpMode opMode, EpsilonRobot robot){
        //instantiating the motors on the hardware map
        frontLeft = opMode.hardwareMap.dcMotor.get("frontLeft");
        frontRight = opMode.hardwareMap.dcMotor.get("frontRight");
        backLeft = opMode.hardwareMap.dcMotor.get("backLeft");
        backRight = opMode.hardwareMap.dcMotor.get("backRight");

        //reverse two motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //set motor runmode
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //set motor zero power behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


}