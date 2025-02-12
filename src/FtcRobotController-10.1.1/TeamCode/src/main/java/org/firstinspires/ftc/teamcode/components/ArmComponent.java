package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.IArmComponent;
import org.firstinspires.ftc.teamcode.math.Angle;
import org.firstinspires.ftc.teamcode.wrappers.motors.MotorWrapper;

/** Implementation of the component that manage the arm motors. */
public class ArmComponent implements IArmComponent {
    private final DcMotorEx leftArmMotor;
    private final DcMotorEx rightArmMotor;
    private Angle targetAngle;
    private static final int COUNTS_PER_WHEEL_REV = 288;
    private static final float MAX_RPS = 0.8f;
    private static final Angle MIN_ANGLE = Angle.fromRadians(0);
    private static final Angle MAX_ANGLE = Angle.fromRadians(Math.PI * 0.75);
    private static final PIDFCoefficients pidfCoefficients = new PIDFCoefficients(
            20, 8, 0, 10);

    public ArmComponent(HardwareMap hardwareMap) {
        leftArmMotor = hardwareMap.get(DcMotorEx.class, "left_arm_motor");
        rightArmMotor = hardwareMap.get(DcMotorEx.class, "right_arm_motor");

        // Check if all this "setting" is really necessary.
        leftArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftArmMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        rightArmMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        leftArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftArmMotor.setTargetPosition(0);
        rightArmMotor.setTargetPosition(0);
        leftArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftArmMotor.setVelocity(MAX_RPS * COUNTS_PER_WHEEL_REV);
        rightArmMotor.setVelocity(MAX_RPS * COUNTS_PER_WHEEL_REV);

        targetAngle = Angle.fromRadians(0);
    }

    public void setAngle(Angle angle) {
        targetAngle = angle.clamp(MIN_ANGLE, MAX_ANGLE);
        leftArmMotor.setTargetPosition((int) (targetAngle.radians() * COUNTS_PER_WHEEL_REV / Math.PI / 2));
        rightArmMotor.setTargetPosition(-(int) (targetAngle.radians() * COUNTS_PER_WHEEL_REV / Math.PI / 2));
    }

    public Angle getAngle() {
        return targetAngle;
    }

    public void shiftAngle(Angle amount) {
        setAngle(targetAngle.add(amount));
    }

    @Override
    public void update() {
        //
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addLine("======== Arm ========");
        telemetry.addData("Left Arm Current", leftArmMotor.getCurrentPosition());
        telemetry.addData("Right Arm Current", rightArmMotor.getCurrentPosition());
    }
}
