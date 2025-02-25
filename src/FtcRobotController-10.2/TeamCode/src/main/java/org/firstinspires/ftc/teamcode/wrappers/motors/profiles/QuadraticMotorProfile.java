package org.firstinspires.ftc.teamcode.wrappers.motors.profiles;

public class QuadraticMotorProfile implements IMotorProfile {
    private final double powerMultiplier;

    public QuadraticMotorProfile() {
        this(1);
    }

    public QuadraticMotorProfile(double powerMultiplier) {
        this.powerMultiplier = powerMultiplier;
    }
    @Override
    public double applyProfile(double power) {
        return Math.signum(power) * power * power * powerMultiplier;
    }
}
