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

        leftPulley.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightPulley.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftPulley.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightPulley.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftPulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightPulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftPulley.setTargetPositionTolerance(TOLERANCE);
        rightPulley.setTargetPositionTolerance(TOLERANCE);



        slidesState = SlidesState.HOLDING;
        platformState = PlatformState.RETRACTED;

    }

    public void extendPlatform() {
        // TODO: Write code to extend platform
    }

    public void retractPlatform() {
        // TODO: Write code to retract platform
    }

    @Override
    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {

        // Handle platform input
        if(gamepad1.right_bumper && platformState == PlatformState.RETRACTED) {
            platformState = PlatformState.EXTENDED;
            extendPlatform();
        } else if(gamepad1.left_bumper && platformState == PlatformState.EXTENDED) {
            platformState = PlatformState.RETRACTED;
            retractPlatform();
        }

        // Handle slides input
        if(gamepad1.a && slidesState == SlidesState.HOLDING) {
            targetHeight = RESET;
            slidesState = SlidesState.MOVING;
        } else if(gamepad1.x && slidesState == SlidesState.HOLDING) {
            targetHeight = HIGH;
            slidesState = SlidesState.MOVING;
        } else if(gamepad1.y && slidesState == SlidesState.HOLDING) {
            targetHeight = MIDDLE;
            slidesState = SlidesState.MOVING;
        } else if(gamepad1.b && slidesState == SlidesState.HOLDING) {
            targetHeight = BOTTOM;
            slidesState = SlidesState.MOVING;
        }

        if(slidesState == SlidesState.MOVING &&
                targetHeight != rightPulley.getTargetPosition()) {
            rightPulley.setTargetPosition(targetHeight);
            leftPulley.setTargetPosition(targetHeight);
        }

        if(slidesState == SlidesState.MOVING &&
                !leftPulley.isBusy() && !rightPulley.isBusy()) {
            slidesState = SlidesState.HOLDING;
        }


    }
}
