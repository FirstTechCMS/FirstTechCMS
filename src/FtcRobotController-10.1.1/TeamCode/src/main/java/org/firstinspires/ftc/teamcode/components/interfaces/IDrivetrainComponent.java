package org.firstinspires.ftc.teamcode.components.interfaces;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.math.Angle;

public interface IDrivetrainComponent extends IComponent {
    void setMotors(double frontLeft, double frontRight, double backLeft, double backRight);
    void setMecanumDrive(Angle heading, double power);
    DcMotor getFrontLeftMotor();
    DcMotor getFrontRightMotor();
    DcMotor getBackLeftMotor();
    DcMotor getBackRightMotor();
}
