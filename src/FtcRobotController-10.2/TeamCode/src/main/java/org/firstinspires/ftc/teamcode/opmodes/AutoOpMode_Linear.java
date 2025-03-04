package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.RobotComponentStore;
import org.firstinspires.ftc.teamcode.components.interfaces.IArmComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;
import org.firstinspires.ftc.teamcode.enums.OpModeId;
import org.firstinspires.ftc.teamcode.math.Angle;

@TeleOp(name = "Auto OpMode", group = "Linear OpMode")
public class AutoOpMode_Linear extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        RobotComponentStore.registerOpModeComponents(OpModeId.AUTONOMOUS, this);

        waitForStart();

        IDrivetrainComponent drivetrain = RobotComponentStore.getComponent(IDrivetrainComponent.class);
        IArmComponent armComponent = RobotComponentStore.getComponent(IArmComponent.class);
        armComponent.setAngle(Angle.fromTurns(0.5));
        sleep(200);
        drivetrain.setMecanumDrive(Angle.fromDegrees(0), 1);
        RobotComponentStore.update();
        RobotComponentStore.updateTelemetry(telemetry);
        sleep(2000);

        RobotComponentStore.update();
        RobotComponentStore.updateTelemetry(telemetry);

        telemetry.update();
    }
}
