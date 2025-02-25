package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Test Arm OpMode", group = "Test")
public class TestArmOpMode_Linear extends LinearOpMode {

    final int COUNTS_PER_WHEEL_REV = 288;
    final float MAX_RPS = 0.5f;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        DcMotorEx leftMotor = hardwareMap.get(DcMotorEx .class, "left_arm_motor");
        DcMotorEx  rightMotor = hardwareMap.get(DcMotorEx .class, "right_arm_motor");

        boolean buttonHandled = false;
        boolean otherSelected = false;
        DcMotorEx activeMotor = leftMotor;

        waitForStart();
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setTargetPosition(0);
        rightMotor.setTargetPosition(0);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive()) {
            if (gamepad1.a && !buttonHandled) {
                otherSelected = !otherSelected;
                activeMotor = otherSelected ? rightMotor : leftMotor;
                buttonHandled = true;
            } else if (!gamepad1.a) {
                buttonHandled = false;
            }

            if (gamepad1.b) {
                activeMotor.setTargetPosition((int)(gamepad1.left_stick_y * COUNTS_PER_WHEEL_REV));
                activeMotor.setVelocity(MAX_RPS * COUNTS_PER_WHEEL_REV);
            }

            telemetry.addData("targetPosition", activeMotor.getTargetPosition());
            telemetry.addData("currentPosition", activeMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}

