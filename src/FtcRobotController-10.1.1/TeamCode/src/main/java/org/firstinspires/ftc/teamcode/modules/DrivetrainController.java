package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DrivetrainController {
    public final DcMotor frontLeftMotor;
    public final DcMotor frontRightMotor;
    public final DcMotor backLeftMotor;
    public final DcMotor backRightMotor;

    public DrivetrainController(HardwareMap hardwareMap){
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_drive");
    }

    public void setMotors(double frontLeft, double frontRight, double backLeft, double backRight){
        frontLeftMotor.setPower(frontLeft);
        frontRightMotor.setPower(frontRight);
        backLeftMotor.setPower(backLeft);
        backRightMotor.setPower(backRight);
    }
}