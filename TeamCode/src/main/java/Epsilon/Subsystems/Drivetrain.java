package Epsilon.Subsystems;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystem;

public class Drivetrain implements Subsystem {

    EpsilonRobot robot;

    public RevIMU imu;
    MecanumDrive drive;

    public Motor frontLeft;
    public Motor frontRight;
    public Motor backLeft;
    public Motor backRight;

    public enum DriveType {
      FIELD_CENTRIC, ROBOT_CENTRIC
    }

    public DriveType driveType = DriveType.ROBOT_CENTRIC;

    public boolean active() {
        return true;
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        GamepadEx driverOp = new GamepadEx(gamepad1);
        if(driveType == DriveType.FIELD_CENTRIC) {
            drive.driveFieldCentric(
                    driverOp.getLeftX(),
                    driverOp.getLeftY(),
                    driverOp.getRightX(),
                    robot.imu.getRevIMU().getRotation2d().getDegrees(),
                    false
            );
        } else if(driveType == DriveType.ROBOT_CENTRIC) {
            drive.driveRobotCentric(
                    driverOp.getLeftX(),
                    driverOp.getLeftY(),
                    driverOp.getRightX(),
                    false
            );
        }

    }

    public void park() throws InterruptedException {

    }

    public void initialize(LinearOpMode opMode, EpsilonRobot fullRobot){

        robot = fullRobot;

        //initializing using FTC library motor class
        frontLeft = new Motor(opMode.hardwareMap, "frontLeft", 537.6, 340);
        frontRight = new Motor(opMode.hardwareMap, "frontRight", 537.6, 340);
        backLeft = new Motor(opMode.hardwareMap, "backLeft", 537.6, 340);
        backRight = new Motor(opMode.hardwareMap, "backRight", 537.6, 340);

        drive = new MecanumDrive(
                frontLeft, frontRight, backLeft, backRight
        );

    }


}