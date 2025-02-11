package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp(name = "Test Motor Controller", group = "Test")
public class TestMotorController_Linear extends LinearOpMode {
    private DcMotorEx motor;
    private final int COUNTS_PER_WHEEL_REV = 288;
    private float angle = 0;
    private float pValue = 40;

    private void setP(float value)
    {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        PIDFCoefficients originalPID = motor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients newPID = new PIDFCoefficients(value, 2.5, originalPID.d, originalPID.f);

        motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, newPID);

        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setVelocity(COUNTS_PER_WHEEL_REV);
    }

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorEx.class, "left_arm_motor");
        waitForStart();

        setP(pValue);

        while(opModeIsActive()) {
            if (gamepad1.b) {
                setP(pValue);
            }
            angle += -gamepad1.left_stick_y * 0.001;
            pValue += -gamepad1.right_stick_y * 0.01;
            motor.setTargetPosition((int)(angle * COUNTS_PER_WHEEL_REV));
            telemetry.addData("Target", angle);
            telemetry.addData("P Value", pValue);
            telemetry.update();
        }
    }
}