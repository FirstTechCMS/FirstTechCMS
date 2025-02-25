package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.RobotComponentStore;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;

@TeleOp(name = "Main OpMode", group = "Linear OpMode")
public class MainOpMode_Linear extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        RobotComponentStore.registerTeleOpComponents(this);

        waitForStart();
        while (opModeIsActive()) {
            IDrivetrainComponent drivetrain = RobotComponentStore.getComponent(IDrivetrainComponent.class);

            RobotComponentStore.update();
            RobotComponentStore.updateTelemetry(telemetry);

            telemetry.update();
        }
    }
}
