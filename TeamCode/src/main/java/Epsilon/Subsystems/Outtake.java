package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystem;

public class Outtake implements Subsystem {

    public DcMotorEx leftPulley;
    public DcMotorEx rightPulley;
    public Servo leftServo;
    public Servo rightServo;

    public enum SlidesState {
        MOVING, HOLDING
    }
    public enum PlatformState {
        EXTENDED, RETRACTED
    }

    public boolean firstServoInputDone = false;
    public double servoVal;

    public SlidesState slidesState;
    public PlatformState platformState;
    public int targetHeight;

    public final int TOLERANCE = 10;

    public final int RESET = 0;
    public final int BOTTOM = 100; // THESE VALUES
    public final int MIDDLE = 150; // ARE JUST
    public final int HIGH = 200; // PLACEHOLDER



    public boolean active() {
        return true;
    }

    public void initialize(LinearOpMode opMode, EpsilonRobot robot) {
        leftPulley = (DcMotorEx) opMode.hardwareMap.dcMotor.get("leftPulley");
        rightPulley = (DcMotorEx) opMode.hardwareMap.dcMotor.get("rightPulley");

        leftServo = opMode.hardwareMap.servo.get("leftOuttakeServo");
        rightServo = opMode.hardwareMap.servo.get("rightOuttakeServo");

        leftPulley.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightPulley.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftPulley.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightPulley.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftPulley.setTargetPosition(0);
        rightPulley.setTargetPosition(0);

        leftPulley.setTargetPositionTolerance(TOLERANCE);
        rightPulley.setTargetPositionTolerance(TOLERANCE);

        leftPulley.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightPulley.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

  //      leftPulley.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
   //     rightPulley.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);



        slidesState = SlidesState.HOLDING;
        platformState = PlatformState.RETRACTED;

    }

    public void extendPlatform() {
        if(!firstServoInputDone) {
            firstServoInputDone = true;
            leftServo.setPosition(0);
            servoVal = 0;
        } else {
            servoVal = Math.min(1, servoVal+.001);
            leftServo.setPosition(servoVal);
        }

        platformState = PlatformState.EXTENDED;
    }

    public void retractPlatform() {
        if(!firstServoInputDone) {
            firstServoInputDone = true;
            leftServo.setPosition(1);
            servoVal = 1;
        } else {
            servoVal = Math.max(0, servoVal-.001);
            leftServo.setPosition(servoVal);
        }
        platformState = PlatformState.RETRACTED;
    }

    @Override
    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {

        // Handle platform input
        if(gamepad2.dpad_up) {
            platformState = PlatformState.EXTENDED;
            extendPlatform();
        } else if(gamepad2.dpad_down) {
            platformState = PlatformState.RETRACTED;
            retractPlatform();
        }

        // Handle slides input
//        if(gamepad1.a && slidesState == SlidesState.HOLDING) {
//            targetHeight = RESET;
//            slidesState = SlidesState.MOVING;
//        } else if(gamepad1.x && slidesState == SlidesState.HOLDING) {
//            targetHeight = HIGH;
//            slidesState = SlidesState.MOVING;
//        } else if(gamepad1.y && slidesState == SlidesState.HOLDING) {
//            targetHeight = MIDDLE;
//            slidesState = SlidesState.MOVING;
//        } else if(gamepad1.b && slidesState == SlidesState.HOLDING) {
//            targetHeight = BOTTOM;
//            slidesState = SlidesState.MOVING;
//        }
//
//        if(slidesState == SlidesState.MOVING &&
//                targetHeight != rightPulley.getTargetPosition()) {
//            rightPulley.setTargetPosition(targetHeight);
//            leftPulley.setTargetPosition(targetHeight);
//        }
//
//        if(slidesState == SlidesState.MOVING &&
//                !leftPulley.isBusy() && !rightPulley.isBusy()) {
//            slidesState = SlidesState.HOLDING;
//        }


    }
}
