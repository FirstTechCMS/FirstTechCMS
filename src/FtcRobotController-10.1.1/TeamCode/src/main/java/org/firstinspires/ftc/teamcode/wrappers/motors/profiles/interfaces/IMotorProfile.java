package org.firstinspires.ftc.teamcode.wrappers.motors.profiles.interfaces;

public interface IMotorProfile {
    double applyProfile(double power);
    double getPowerMultiplier();
    double setPowerMultiplier(double powerMultiplier);
}
