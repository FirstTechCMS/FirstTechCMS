package org.firstinspires.ftc.teamcode.wrappers.motors.profiles;

public class LinearMotorProfile implements IMotorProfile {
    private final double powerMultiplier;

    public LinearMotorProfile() {
        this(1);
    }

    public LinearMotorProfile(double powerMultiplier) {
        this.powerMultiplier = powerMultiplier;
    }

    public double applyProfile(double power) {
        return power * powerMultiplier;
    }
}
