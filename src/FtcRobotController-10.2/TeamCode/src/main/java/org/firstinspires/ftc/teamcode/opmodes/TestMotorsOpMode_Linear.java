package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Test Motors OpMode", group="Test")
public class TestMotorsOpMode_Linear extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        DcMotor[] motors = new DcMotor[4];

        DcMotor frontLeftMotor = motors[0] = hardwareMap.get(DcMotor.class, "front_left_motor");
        DcMotor frontRightMotor = motors[1] = hardwareMap.get(DcMotor.class, "front_right_motor");
        DcMotor backLeftMotor = motors[2] = hardwareMap.get(DcMotor.class, "back_left_motor");
        DcMotor backRightMotor = motors[3] = hardwareMap.get(DcMotor.class, "back_right_motor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        int activeIndex = 0;
        boolean buttonHandled = false;

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a && !buttonHandled) {
                motors[activeIndex].setPower(0);
                activeIndex  = (activeIndex + 1) % motors.length;
                buttonHandled = true;
            } else if (!gamepad1.a) {
                buttonHandled = false;
            }

            motors[activeIndex].setPower(gamepad1.left_stick_y);
            telemetry.addData("Active Motor", activeIndex);
            telemetry.addData("Active Motor", motors[activeIndex].getDeviceName());
            telemetry.addData("Power", gamepad1.left_stick_y);
            telemetry.update();
        }
    }
}
