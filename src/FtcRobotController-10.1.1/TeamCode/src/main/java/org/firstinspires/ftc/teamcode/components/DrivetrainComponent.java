package org.firstinspires.ftc.teamcode.components;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.ISensorComponent;
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
    private final double turnMultiplier = 0.05;

    public DrivetrainComponent(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        movePower = 0;
        moveDirection = Angle.fromRadians(0);
        lookDirection = Angle.fromRadians(0);
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
        lookDirection = heading;
    }

    public void turnLookDirection(Angle amount) {
        lookDirection = lookDirection.add(amount);
    }

    public Angle getLookDirection() {
        return lookDirection;
    }

    public void update() {
        ISensorComponent sensorComponent = RobotComponentStore.getComponent(ISensorComponent.class);
        Angle currentHeading = sensorComponent.getHeading();

        Angle relativeMoveDirection = moveDirection.subtract(currentHeading);
        Angle relativeLookDirection = lookDirection.subtract(currentHeading);

        double turnPower = relativeLookDirection.radians() * turnMultiplier;

        double leftDiagonal = movePower * relativeMoveDirection.add(Math.PI / 4).cos();
        double rightDiagonal = movePower * relativeMoveDirection.subtract(Math.PI / 4).cos();

        double frontLeft = leftDiagonal + turnPower;
        double frontRight = rightDiagonal - turnPower;
        double backLeft = rightDiagonal + turnPower;
        double backRight = leftDiagonal - turnPower;

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
        telemetry.addData("Relative look dir", moveDirection.subtract(RobotComponentStore.getComponent(ISensorComponent.class).getHeading()));
    }
}