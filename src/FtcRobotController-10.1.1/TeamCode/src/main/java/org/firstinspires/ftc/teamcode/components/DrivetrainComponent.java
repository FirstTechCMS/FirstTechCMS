package org.firstinspires.ftc.teamcode.components;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.ISensorComponent;
import org.firstinspires.ftc.teamcode.math.Angle;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;
import org.firstinspires.ftc.teamcode.wrappers.motors.MotorWrapper;
import org.firstinspires.ftc.teamcode.wrappers.motors.profiles.LinearMotorProfile;
import org.firstinspires.ftc.teamcode.wrappers.motors.profiles.QuadraticMotorProfile;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Implementation of the component that manages the drivetrain motors.
 */
public class DrivetrainComponent implements IDrivetrainComponent {
    private final MotorWrapper frontLeftMotor;
    private final MotorWrapper frontRightMotor;
    private final MotorWrapper backLeftMotor;
    private final MotorWrapper backRightMotor;
    private double movePower;
    private Angle moveDirection;
    private Angle lookDirection;
    private final double turnMultiplier = 0.05;
    private final double frontMotorMultiplier = 2./3;
    public DrivetrainComponent(HardwareMap hardwareMap) {
        frontLeftMotor = new MotorWrapper(hardwareMap, "front_left_motor", DcMotorSimple.Direction.REVERSE, new LinearMotorProfile(frontMotorMultiplier));
        frontRightMotor = new MotorWrapper(hardwareMap, "front_right_motor", DcMotorSimple.Direction.FORWARD, new LinearMotorProfile(frontMotorMultiplier));
        backLeftMotor = new MotorWrapper(hardwareMap, "back_left_motor", DcMotorSimple.Direction.REVERSE, new LinearMotorProfile());
        backRightMotor = new MotorWrapper(hardwareMap, "back_right_motor", DcMotorSimple.Direction.FORWARD, new LinearMotorProfile());

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

        Angle relativeMoveDirection = moveDirection; //.subtract(currentHeading);
        Angle relativeLookDirection = lookDirection.subtract(currentHeading);

        double turnPower = 0; //relativeLookDirection.radians() * turnMultiplier;

        double downDiagonal = movePower * relativeMoveDirection.add(Math.PI / 4).cos();
        double upDiagonal = movePower * relativeMoveDirection.subtract(Math.PI / 4).cos();

        double frontLeft = upDiagonal + turnPower;
        double frontRight = downDiagonal - turnPower;
        double backLeft = downDiagonal + turnPower;
        double backRight = upDiagonal - turnPower;

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