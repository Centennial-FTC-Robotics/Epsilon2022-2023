package Epsilon.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera2;

import java.util.ArrayList;

import Epsilon.EpsilonRobot;
import Epsilon.Subsystem;
import Epsilon.util.AprilTagDetectionPipeline;

public class Camera implements Subsystem {

    public OpenCvCamera camera;
    public AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // WE NEED to CALIBRATE EVERYTHING BELOW -JOEL

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    public final double fx = 578.272;
    public final double fy = 578.272;
    public final double cx = 402.145;
    public final double cy = 221.506;

    // UNITS ARE METERS
    public final double tagsize = 0.166;

    int numFramesWithoutDetection = 0;

    final float DECIMATION_HIGH = 3;
    final float DECIMATION_LOW = 2;
    final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;

    public final int[] tag_ids = {21, 69, 420};

    @Override
    public void initialize(LinearOpMode opMode, EpsilonRobot robot) {

        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());

        camera = OpenCvCameraFactory.getInstance().createInternalCamera2(OpenCvInternalCamera2.CameraDirection.BACK, cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);


        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(1280,720, OpenCvCameraRotation.UPRIGHT);
                camera.setPipeline(aprilTagDetectionPipeline);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

    }

    @Override
    public boolean active() {
        return false;
    }

    // 1 2 or 3 depending on parkign station
    public int scanParking() {
        ArrayList<AprilTagDetection> detections = aprilTagDetectionPipeline.getLatestDetections();
        if (detections.size() != 0) {
            for(AprilTagDetection tag : detections)
            {
                for(int i = 0; i < tag_ids.length; i++) {
                    if(tag_ids[i] == tag.id) {
                        return i+1;
                    }
                }
            }
        }
        return 0;
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {}
}
