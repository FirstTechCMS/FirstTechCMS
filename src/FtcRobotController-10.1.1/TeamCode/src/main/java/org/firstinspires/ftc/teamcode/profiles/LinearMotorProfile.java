package org.firstinspires.ftc.teamcode.profiles;

public class LinearMotorProfile {
    private double turnMultiplier;

    public LinearMotorProfile(double turnMultiplier) {
        this.turnMultiplier = turnMultiplier;
    }

    public double getTranformedPower(double power) {
        return power * turnMultiplier;
    }

    public double getTurnMultiplier() {
        return turnMultiplier;
    }

    public double setTurnMultiplier(double turnMultiplier) {
        this.turnMultiplier = turnMultiplier;
        return turnMultiplier;
    }
}
