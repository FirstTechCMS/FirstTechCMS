package org.firstinspires.ftc.teamcode.wrappers.motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.wrappers.motors.profiles.LinearMotorProfile;
import org.firstinspires.ftc.teamcode.wrappers.motors.profiles.IMotorProfile;

public class MotorWrapper {
    private final DcMotor motor;
    private final IMotorProfile profile;


    public MotorWrapper(HardwareMap map, String motorName, DcMotorSimple.Direction direction) {
        this(map, motorName, direction, new LinearMotorProfile());
    }

    public MotorWrapper(HardwareMap map, String motorName, DcMotorSimple.Direction direction, IMotorProfile profile) {
        motor = map.get(DcMotor.class, motorName);
        motor.setDirection(direction);
        this.profile = profile;
    }

    public void setPower(double power) {
        motor.setPower(profile.applyProfile(power));
    }
    public double getPower() {
        return motor.getPower();
    }
}
