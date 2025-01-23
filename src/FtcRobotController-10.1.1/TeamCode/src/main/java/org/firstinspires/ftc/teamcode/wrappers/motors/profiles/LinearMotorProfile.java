package org.firstinspires.ftc.teamcode.wrappers.motors.profiles;

public class LinearMotorProfile {
    private double powerMultiplier;

    public LinearMotorProfile(double turnMultiplier) {
        this.powerMultiplier = turnMultiplier;
    }

    public double applyProfile(double power) {
        return power * powerMultiplier;
    }

    public double getPowerMultiplier() {
        return powerMultiplier;
    }

    public double setPowerMultiplier(double powerMultiplier) {
        this.powerMultiplier = powerMultiplier;
        return powerMultiplier;
    }
}
