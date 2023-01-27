package Epsilon;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import Epsilon.Subsystems.Camera;
import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.IMU;
import Epsilon.Subsystems.Intake;
import Epsilon.Subsystems.Outtake;

public class EpsilonRobot {

    public Drivetrain drivetrain = new Drivetrain();
    public Intake intake = new Intake();
    public Outtake outtake = new Outtake();
    public Camera camera = new Camera();
    public IMU imu = new IMU();
    public Subsystem[] subsystems = new Subsystem[] {
      drivetrain, intake, outtake, camera, imu
    };

    public void initialize(LinearOpMode opMode){
        for(Subsystem subsystem : subsystems) {
            if(subsystem.active()) {
                subsystem.initialize(opMode, this);
            }
        }
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) throws InterruptedException {
        for(Subsystem subsystem : subsystems) {
            if(subsystem.active()) {
                subsystem.teleOpUpdate(gamepad1, gamepad2);
            }
        }
    }
}
