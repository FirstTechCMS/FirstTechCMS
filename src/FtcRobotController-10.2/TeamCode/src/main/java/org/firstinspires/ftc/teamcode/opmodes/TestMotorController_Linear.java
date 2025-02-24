package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp(name = "Test Motor Controller", group = "Test")
public class TestMotorController_Linear extends LinearOpMode {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private final int COUNTS_PER_WHEEL_REV = 288;
    private double angle = 0;
    private PIDFCoefficients coefficients;

    private void setCoefficients(PIDFCoefficients coefficients)
    {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        PIDFCoefficients originalPID = leftMotor.getPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION);
        PIDFCoefficients newPID = new PIDFCoefficients(coefficients);

        leftMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, newPID);
        rightMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, newPID);

        leftMotor.setTargetPosition(0);
        rightMotor.setTargetPosition(9);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setVelocity(COUNTS_PER_WHEEL_REV);
        rightMotor.setVelocity(COUNTS_PER_WHEEL_REV);
    }

    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotorEx.class, "left_arm_motor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "right_arm_motor");
        coefficients = leftMotor.getPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION);
        waitForStart();

        setCoefficients(coefficients);

        while(opModeIsActive()) {
            if (gamepad1.b) {
                setCoefficients(coefficients);
            }
            angle += -gamepad1.left_stick_y * 0.001;
            coefficients.p += -gamepad1.right_stick_y * 0.01;
            leftMotor.setTargetPosition((int)(angle * COUNTS_PER_WHEEL_REV));
            rightMotor.setTargetPosition(-(int)(angle * COUNTS_PER_WHEEL_REV));
            telemetry.addData("Target", angle);
            telemetry.addData("P", coefficients.p);
            telemetry.addData("I", coefficients.i);
            telemetry.addData("D", coefficients.d);
            telemetry.update();
        }
    }
}