package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.RobotComponentStore;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;
import org.firstinspires.ftc.teamcode.enums.OpModeId;

@TeleOp(name = "Main OpMode", group = "Linear OpMode")
public class MainOpMode_Linear extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        RobotComponentStore.registerOpModeComponents(OpModeId.TELEOP, this);

        waitForStart();
        while (opModeIsActive()) {
            RobotComponentStore.update();
            RobotComponentStore.updateTelemetry(telemetry);

            telemetry.update();
        }
    }
}
