package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.RobotComponentStore;
import org.firstinspires.ftc.teamcode.components.interfaces.IArmComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.ISensorComponent;
import org.firstinspires.ftc.teamcode.enums.OpModeId;
import org.firstinspires.ftc.teamcode.math.Angle;

@Autonomous(name = "Test Sensors", group = "Test")
public class TestSensorOpMode_Linear extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        RobotComponentStore.registerOpModeComponents(OpModeId.AUTONOMOUS, this);

        waitForStart();
        ISensorComponent sensor = RobotComponentStore.getComponent(ISensorComponent.class);
        IDrivetrainComponent drivetrain = RobotComponentStore.getComponent(IDrivetrainComponent.class);

        // Move forward until the something is in the way of the sensor
        drivetrain.setMecanumDrive(Angle.fromDegrees(0), 0.1);
        while (opModeIsActive()) {
            double distance = sensor.getDistanceLeft();
            if (distance < 10) {
                drivetrain.setMecanumDrive(Angle.fromDegrees(0), 0);
            }

            telemetry.addData("Distance", distance);
            telemetry.update();
        }
        sleep(1000);


        RobotComponentStore.update();
        RobotComponentStore.updateTelemetry(telemetry);

        telemetry.update();
    }
}
