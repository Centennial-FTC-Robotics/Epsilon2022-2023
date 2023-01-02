package Epsilon.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class EpsilonRobot {

    public static Drivetrain drivetrain = new Drivetrain();

    public static void initialize(LinearOpMode opMode){
        drivetrain.initialize(opMode);
    }
}
