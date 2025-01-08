package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.math.Angle;
import org.firstinspires.ftc.teamcode.modules.RobotComponentStore;
import org.firstinspires.ftc.teamcode.modules.interfaces.IDrivetrainComponent;

@TeleOp(name="Main OpMode", group="Linear OpMode")
public class MainOpMode_Linear extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        RobotComponentStore.registerTeleOpMode(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            IDrivetrainComponent drivetrain = RobotComponentStore.getComponent(IDrivetrainComponent.class);

            double horizontal = gamepad1.left_stick_x;
            double vertical = -gamepad1.left_stick_y;
            double power = Math.hypot(horizontal, vertical);
            Angle angle = Angle.fromAtan2(vertical, horizontal);

            drivetrain.setMecanumDrive(angle, power);
            drivetrain.update();
            drivetrain.updateTelemetry(telemetry);
        }
    }
}
