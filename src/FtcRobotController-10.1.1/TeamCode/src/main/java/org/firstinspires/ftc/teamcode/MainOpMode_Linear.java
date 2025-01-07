package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.modules.DrivetrainController;

@TeleOp(name="Main OpMode", group="Linear OpMode")
public class MainOpMode_Linear extends LinearOpMode {
    private DrivetrainController drivetrainController;
    private void updateTelemetry(){
        telemetry.addLine("================ Controls ================");
        telemetry.addData("Gamepad 1", String.format("X: %f, Y: %f", gamepad1.left_stick_x, gamepad1.left_stick_y));
        telemetry.addData("Gamepad 2", String.format("X: %f, Y: %f", gamepad2.left_stick_x, gamepad2.left_stick_y));
        telemetry.addLine("================ Motors ================");
        telemetry.addData("Front Left", drivetrainController.frontLeftMotor.getPower());
        telemetry.addData("Front Right", drivetrainController.frontRightMotor.getPower());
        telemetry.addData("Back Left", drivetrainController.backLeftMotor.getPower());
        telemetry.addData("Back Right", drivetrainController.backRightMotor.getPower());
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        drivetrainController = new DrivetrainController(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            double horizontal = gamepad1.left_stick_x;
            double vertical = -gamepad1.left_stick_y;

            double power = Math.hypot(vertical, horizontal);
            double angle = Math.atan2(horizontal, vertical);

            drivetrainController.setMecanumDrive(angle,power);
            drivetrainController.update();
        }
    }
}
