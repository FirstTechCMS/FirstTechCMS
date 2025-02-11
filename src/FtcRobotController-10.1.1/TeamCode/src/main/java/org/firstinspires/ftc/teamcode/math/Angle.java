package org.firstinspires.ftc.teamcode.math;

import androidx.annotation.NonNull;

/**
 * Represents an immutable angle in radians, from -&pi; to &pi;. </br>
 * Use <code>Angle.fromRadians(x)</code> or similar to instantiate.
 */
public class Angle {
    final double internalAngle;

    private double flooredModulo(double x, double y) {
        return x - y * Math.floor(x / y);
    }

    private double normalizeAngle(double angle) {
        return (angle + Math.PI %( 2 * Math.PI)) - Math.PI;
    }

    private Angle(double angle) {
        internalAngle = normalizeAngle(angle);
    }

    public Angle add(Angle other) {
        return new Angle(internalAngle + other.radians());
    }

    public Angle add(double other) {
        return new Angle(internalAngle + other);
    }

    public Angle subtract(Angle other) {
        return new Angle(internalAngle - other.radians());
    }

    public Angle subtract(double other) {
        return new Angle(internalAngle - other);
    }

    public Angle max(Angle other) {
        return new Angle(Math.max(internalAngle, other.radians()));
    }

    public Angle min(Angle other) {
        return new Angle(Math.min(internalAngle, other.radians()));
    }

    public Angle clamp(Angle min, Angle max) {
        return new Angle(Math.min(Math.max(internalAngle, min.radians()), max.radians()));
    }

    public double radians() {
        return internalAngle;
    }

    public double degrees() {
        return internalAngle / Math.PI * 180;
    }

    public double cos() {
        return Math.cos(internalAngle);
    }

    public double sin() {
        return Math.sin(internalAngle);
    }

    public double shortestTurnTo(Angle other) {
        double difference = other.radians() - radians();
        if (difference < -Math.PI)
            return difference + Math.PI;
        else if (difference > Math.PI)
            return difference - Math.PI;
        else
            return difference;
    }

    public static Angle fromRadians(double radians) {
        return new Angle(radians);
    }

    public static Angle fromTurns(double factor) {
        return new Angle(factor * Math.PI * 2);
    }

    public static Angle fromDegrees(double degrees) {
        return new Angle(degrees / 180 * Math.PI);
    }

    public static Angle fromAtan2(double x, double y) {
        return new Angle(Math.atan2(y, x));
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%f rad", internalAngle);
    }
}