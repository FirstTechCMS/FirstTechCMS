package org.firstinspires.ftc.teamcode.wrappers.motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.wrappers.motors.profiles.interfaces.IMotorProfile;

public class MotorWrapper {
    private final DcMotor motor;
    private double power;
    private final DcMotorSimple.Direction direction;
    private final IMotorProfile profile;

    public MotorWrapper(HardwareMap map, String motorName, DcMotorSimple.Direction direction, IMotorProfile profile) {
        this.motor = map.get(DcMotor.class, motorName);
        this.profile = profile;
        this.direction = direction;
        motor.setDirection(direction);
    }

    public void setPower(double power) {
        this.power = profile.applyProfile(power);
        motor.setPower(power);
    }
    public double getPower() {
        return power;
    }
}
