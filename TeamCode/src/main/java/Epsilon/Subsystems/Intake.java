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

    public static final int ArmOut = 290;
    public static final int ArmIn = 0;

    public final double GrabberFullOpen = .2;
    public final double GrabberSafeOpen = .3;
    public final double GrabberClosed = .4;

    public int armTarget = ArmIn;
    public double grabberTarget = GrabberSafeOpen;

    public enum ArmState {
        EXTENDING, RETRACTING, STILL
    }
    public ArmState armState = ArmState.STILL;

    public final double ticks_in_degree = 537.7/360;

    public DcMotorEx fourBar;
    public Servo grabber;
    public boolean grabberClosed = true;

    public boolean lastLeftBumper = false;
    public boolean lastRightBumper = false;
    public boolean lastA = false;

    public LinearOpMode opMode;

    @Override
    public void initialize(LinearOpMode om, EpsilonRobot robot) {
        opMode = om;

        controller = new PIDController(p, i, d);
        controller.setTolerance(20);
        fourBar = opMode.hardwareMap.get(DcMotorEx.class, "fourBar");
        fourBar.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fourBar.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        grabber = opMode.hardwareMap.servo.get("grabber");



    }



    public void PID(){
        int armPos = fourBar.getCurrentPosition();
        controller.setSetPoint(armTarget);
        double pid = controller.calculate(armPos, armTarget);
        double ff = Math.cos(Math.toRadians(armTarget/ticks_in_degree)) * f;

        fourBar.setPower(pid + ff);
    }

    public void setGrabber(double grabberPos) {
        grabberTarget = grabberPos;
        grabber.setPosition(grabberTarget);
    }

    public void armOut() throws InterruptedException {
        if(grabberTarget != GrabberSafeOpen) {
            setGrabber(GrabberSafeOpen);
            Thread.sleep(300);
        }
        armTarget = ArmOut;
        armState = ArmState.EXTENDING;
    }

    public void armIn() throws InterruptedException {
        if(grabberTarget == GrabberFullOpen) {
            setGrabber(GrabberSafeOpen);
            Thread.sleep(300);
        }
        armTarget = ArmIn;
        armState = ArmState.RETRACTING;
    }

    public boolean active() {
        return true;
    }

    @Override
    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) throws InterruptedException {

        if(armState == ArmState.EXTENDING || armState == ArmState.RETRACTING) {
            PID();
            if(controller.atSetPoint()) {
                fourBar.setPower(0);
                Thread.sleep(300);
                if(armState == ArmState.EXTENDING) {
                    setGrabber(GrabberFullOpen);
                } else if(armState == ArmState.RETRACTING) {
                    setGrabber(GrabberSafeOpen);
                }

                armState = ArmState.STILL;

            }
        } else {
            if(gamepad2.left_bumper && !lastLeftBumper) {
                armOut();
            } else if(gamepad2.a && !lastA) {
                setGrabber(GrabberClosed);
            } else if(gamepad2.right_bumper && !lastRightBumper) {
                armIn();
            }
        }

        lastLeftBumper = gamepad2.left_bumper;
        lastRightBumper = gamepad2.right_bumper;
        lastA = gamepad2.a;

        opMode.telemetry.addData("grabberTarget", grabberTarget);
        opMode.telemetry.addData("grabberActual", fourBar.getCurrentPosition());
        opMode.telemetry.addData("armTarget", armTarget);
        opMode.telemetry.addData("atTarget", controller.atSetPoint());

    }
}
