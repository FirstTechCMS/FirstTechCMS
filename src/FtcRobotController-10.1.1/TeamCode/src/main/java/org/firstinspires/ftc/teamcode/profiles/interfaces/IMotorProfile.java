package org.firstinspires.ftc.teamcode.profiles.interfaces;

public interface IMotorProfile {
    double getTranformedPower(double power);
    double getTurnMultiplier();
    double setTurnMultiplier(double turnMultiplier);
}
