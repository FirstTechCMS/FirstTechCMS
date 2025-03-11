package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.RobotComponentStore;
import org.firstinspires.ftc.teamcode.components.interfaces.IArmComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.ISensorComponent;
import org.firstinspires.ftc.teamcode.enums.OpModeId;
import org.firstinspires.ftc.teamcode.math.Angle;

@TeleOp(name = "Auto OpMode", group = "Linear OpMode")
public class ForwardOpMode_Linear extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        RobotComponentStore.registerOpModeComponents(OpModeId.AUTONOMOUS, this);

        waitForStart();
        ISensorComponent sensor = RobotComponentStore.getComponent(ISensorComponent.class);
        IDrivetrainComponent drivetrain = RobotComponentStore.getComponent(IDrivetrainComponent.class);

        drivetrain.setMecanumDrive(Angle.fromDegrees(0), 0.3);
        while (opModeIsActive()) {
            let distance = sensor.getDistance();
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
