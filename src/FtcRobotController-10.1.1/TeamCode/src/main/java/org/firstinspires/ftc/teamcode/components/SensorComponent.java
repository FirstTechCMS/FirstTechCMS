package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.teamcode.components.interfaces.ISensorComponent;
import org.firstinspires.ftc.teamcode.math.Angle;

/** Implementation of the component that manages sensors. */
public class SensorComponent implements ISensorComponent {
    private final BHI260IMU imu;

    public SensorComponent(HardwareMap hardwareMap) {
        imu = hardwareMap.get(BHI260IMU.class, "imu");

        BHI260IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        new Quaternion(1.0f, 0.0f, 0.0f, 0.0f, 0)
                )
        );
    }

    /**
     * Returns the horizontal direction the robot is facing in radians, relative to the starting orientation.
     */
    public Angle getHeading() {
        return Angle.fromRadians(imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
    }

    @Override
    public void update() {
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addLine("======== Sensor ========");
        telemetry.addData("Heading", getHeading());
    }
}
