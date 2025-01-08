package org.firstinspires.ftc.teamcode.components;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.math.Angle;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Implementation of the component that manages the drivetrain motors.
 * */
public class DrivetrainComponent implements IDrivetrainComponent {
    private final DcMotor frontLeftMotor;
    private final DcMotor frontRightMotor;
    private final DcMotor backLeftMotor;
    private final DcMotor backRightMotor;
    private double movePower;
    private Angle moveDirection;
    private Angle lookDirection;

    public DrivetrainComponent(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void setMotors(double frontLeft, double frontRight, double backLeft, double backRight) {
        frontLeftMotor.setPower(frontLeft);
        frontRightMotor.setPower(frontRight);
        backLeftMotor.setPower(backLeft);
        backRightMotor.setPower(backRight);
    }

    @Override
    public void setMecanumDrive(Angle heading, double power) {
        movePower = power;
        moveDirection = heading;
    }

    public void setLookDirection(Angle heading) {

    }

    public void update() {
        double leftDiagonal = movePower * moveDirection.add(Math.PI / 4).cos();
        double rightDiagonal = movePower * moveDirection.subtract(Math.PI / 4).cos();

        double frontLeft = leftDiagonal;
        double frontRight = rightDiagonal;
        double backLeft = rightDiagonal;
        double backRight = leftDiagonal;

        setMotors(frontLeft, frontRight, backLeft, backRight);
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addLine("======== Drivetrain ========");
        telemetry.addData("Move Power", movePower);
        telemetry.addData("Move Direction", moveDirection);
        telemetry.addData("Look Direction", lookDirection);
        telemetry.addData("Front Left Motor", frontLeftMotor.getPower());
        telemetry.addData("Front Right Motor", frontRightMotor.getPower());
        telemetry.addData("Back Left Motor", backLeftMotor.getPower());
        telemetry.addData("Back Right Motor", backRightMotor.getPower());
    }
}