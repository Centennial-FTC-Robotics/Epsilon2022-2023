package Epsilon.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

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
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

    }

    @Override
    public boolean active() {
        return true;
    }

    // 1 2 or 3 depending on parkign station
    public int scanParking() {
        while(true) {
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
        }
    }

    @Override
    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {}
}
