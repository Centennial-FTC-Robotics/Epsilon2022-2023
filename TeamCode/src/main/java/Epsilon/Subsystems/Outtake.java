package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystem;

public class Outtake implements Subsystem {

    public DcMotorEx outtakePulley;
    public Servo platform;

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
        outtakePulley = (DcMotorEx) opMode.hardwareMap.dcMotor.get("outtakePulley");
        platform = opMode.hardwareMap.servo.get("platform");

        outtakePulley.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        outtakePulley.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        //outtakePulley.setTargetPosition(0);
        //outtakePulley.setTargetPositionTolerance(TOLERANCE);
        outtakePulley.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        slidesState = SlidesState.HOLDING;
        platformState = PlatformState.RETRACTED;
    }

    public void extendPlatform() {
        platform.setPosition(0.9);
        platformState = PlatformState.EXTENDED;
    }

    public void retractPlatform() {
       platform.setPosition(0.5);
       platformState = PlatformState.RETRACTED;
    }

    public double holdMotor(double targetPosition){
        double kP = 0.05;   //constant, may need tuning
        double power;
        double error = targetPosition - outtakePulley.getCurrentPosition();
        power = error * kP;
        return power;
    }

    @Override
    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        //manual outtake
        if(gamepad2.dpad_up){
            if(gamepad2.right_trigger > 0){
                outtakePulley.setPower(-0.3);
            } else {
                outtakePulley.setPower(-0.5);
            }
        } else if(gamepad2.dpad_down){
            if(gamepad2.right_trigger > 0){
                outtakePulley.setPower(0.3);
            } else {
                outtakePulley.setPower(0.5);
            }
            outtakePulley.setPower(0.5);
        } else {
            outtakePulley.setPower(holdMotor(outtakePulley.getCurrentPosition()));
        }

        if(gamepad2.b){
            extendPlatform();
        } else {
            retractPlatform();
        }

        //preset positions
        /*
        // Handle platform input
        if(gamepad2.dpad_up) {
            platformState = PlatformState.EXTENDED;
            extendPlatform();
        } else if(gamepad2.dpad_down) {
            platformState = PlatformState.RETRACTED;
            retractPlatform();
        }

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
                targetHeight != outtakePulley.getTargetPosition()) {
            outtakePulley.setTargetPosition(targetHeight);
            slidesState = SlidesState.HOLDING;
        }
         */

    }
}
