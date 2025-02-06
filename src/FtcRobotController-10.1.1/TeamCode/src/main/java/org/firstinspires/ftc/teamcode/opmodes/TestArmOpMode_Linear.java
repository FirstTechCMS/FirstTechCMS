package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test Arm OpMode", group = "Linear OpMode")
public class TestArmOpMode_Linear extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "left_arm_motor");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "right_arm_motor");

        leftMotor.setTargetPosition(0);
        rightMotor.setTargetPosition(0);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        boolean buttonHandled = false;
        boolean otherSelected = false;
        DcMotor activeMotor = leftMotor;

        final int multiplier = 1440;

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a && !buttonHandled) {
                otherSelected = !otherSelected;
                activeMotor = otherSelected ? rightMotor : leftMotor;
                buttonHandled = true;
            } else if (!gamepad1.a) {
                buttonHandled = false;
            }

            if (gamepad1.b) {
                activeMotor.setTargetPosition((int)(gamepad1.left_stick_y * multiplier));
            }

            telemetry.addData("targetPosition", activeMotor.getTargetPosition());
            telemetry.addData("currentPosition", activeMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}
