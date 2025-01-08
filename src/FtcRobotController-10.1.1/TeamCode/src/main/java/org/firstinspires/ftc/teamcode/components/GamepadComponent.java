package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.ICommandComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;
import org.firstinspires.ftc.teamcode.math.Angle;

/**
 * Implementation of the component that responds to gamepad inputs and controls other components.
 * */
public class GamepadComponent implements ICommandComponent {
    private final Gamepad gamepad1;
    private final Gamepad gamepad2;

    public GamepadComponent(Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    @Override
    public void update() {
        IDrivetrainComponent drivetrainComponent = RobotComponentStore.getComponent(IDrivetrainComponent.class);

        double horizontalDrive = gamepad1.left_stick_x;
        double verticalDrive = -gamepad1.left_stick_y;

        double power = Math.hypot(horizontalDrive, verticalDrive);
        Angle angle = Angle.fromAtan2(verticalDrive, horizontalDrive);

        drivetrainComponent.setMecanumDrive(angle, power);
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addLine("======== Gamepad ========");
        telemetry.addData("1_Left", "X: %.2f, Y: %.2f", gamepad1.left_stick_x, -gamepad1.left_stick_y);
        telemetry.addData("1_Right", "X: %.2f, Y: %.2f", gamepad1.right_stick_x, -gamepad1.right_stick_y);
        telemetry.addData("2_Left", "X: %.2f, Y: %.2f", gamepad2.left_stick_x, -gamepad2.left_stick_y);
        telemetry.addData("2_Right", "X: %.2f, Y: %.2f", gamepad1.right_stick_x, -gamepad1.right_stick_y);
    }
}
