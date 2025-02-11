package org.firstinspires.ftc.teamcode.components;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.ISensorComponent;
import org.firstinspires.ftc.teamcode.math.Angle;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;
import org.firstinspires.ftc.teamcode.wrappers.motors.MotorWrapper;
import org.firstinspires.ftc.teamcode.wrappers.motors.profiles.LinearMotorProfile;

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
    private double turnPower;
    private double movePower;
    private Angle moveDirection;
    private static final double TURN_MULTIPLIER = 1;
    private static final double FRONT_MOTOR_MULTIPLIER = 2. / 3;

    public DrivetrainComponent(HardwareMap hardwareMap) {
        frontLeftMotor = new MotorWrapper(hardwareMap, "front_left_motor", DcMotorSimple.Direction.REVERSE, new LinearMotorProfile(FRONT_MOTOR_MULTIPLIER));
        frontRightMotor = new MotorWrapper(hardwareMap, "front_right_motor", DcMotorSimple.Direction.FORWARD, new LinearMotorProfile(FRONT_MOTOR_MULTIPLIER));
        backLeftMotor = new MotorWrapper(hardwareMap, "back_left_motor", DcMotorSimple.Direction.REVERSE, new LinearMotorProfile());
        backRightMotor = new MotorWrapper(hardwareMap, "back_right_motor", DcMotorSimple.Direction.FORWARD, new LinearMotorProfile());

        movePower = 0;
        turnPower = 0;
        moveDirection = Angle.fromRadians(0);
    }

    @Override
    public void setTurnPower(double power) {
        turnPower = power;
    }

    @Override
    public double getTurnPower() {
        return turnPower;
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

    @Override
    public void update() {
        ISensorComponent sensorComponent = RobotComponentStore.getComponent(ISensorComponent.class);

        double downDiagonal = movePower * moveDirection.add(Math.PI / 4).cos();
        double upDiagonal = movePower * moveDirection.subtract(Math.PI / 4).cos();

        double frontLeft = upDiagonal + turnPower * TURN_MULTIPLIER;
        double frontRight = downDiagonal - turnPower * TURN_MULTIPLIER;
        double backLeft = downDiagonal + turnPower * TURN_MULTIPLIER;
        double backRight = upDiagonal - turnPower * TURN_MULTIPLIER;

        setMotors(frontLeft, frontRight, backLeft, backRight);
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addLine("======== Drivetrain ========");
        telemetry.addData("Move Power", movePower);
        telemetry.addData("Move Direction", moveDirection);
        telemetry.addData("Front Left Motor", frontLeftMotor.getPower());
        telemetry.addData("Front Right Motor", frontRightMotor.getPower());
        telemetry.addData("Back Left Motor", backLeftMotor.getPower());
        telemetry.addData("Back Right Motor", backRightMotor.getPower());
        telemetry.addData("Look Direction", Math.round(moveDirection.radians() * 180 / Math.PI));
    }
}