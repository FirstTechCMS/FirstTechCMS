package org.firstinspires.ftc.teamcode.math;

/**
 * Represents an angle in radians, from -π to π.
 * Immutable. If this were a superior language, it would be a struct.
 */
public class Angle {
    final double internalAngle;
    private double normalizeAngle(double angle){
        return ((angle + Math.PI) % (2 * Math.PI)) - Math.PI;
    }
    private Angle(double angle) {
        internalAngle = normalizeAngle(angle);
    }
    public Angle add(Angle other){
        return new Angle(internalAngle + other.radians());
    }
    public Angle add(double other){
        return new Angle(internalAngle + other);
    }
    public Angle subtract(Angle other){
        return new Angle(internalAngle - other.radians());
    }
    public Angle subtract(double other){
        return new Angle(internalAngle - other);
    }
    public double radians() {
        return internalAngle;
    }
    public double degrees(){
        return internalAngle / Math.PI * 180;
    }

    public double cos(){
        return Math.cos(internalAngle);
    }

    public double sin(){
        return Math.sin(internalAngle);
    }
    public static Angle fromRadians(double radians){
        return new Angle(radians);
    }
    public static Angle fromDegrees(double degrees) {
        return new Angle(degrees / 180 * Math.PI);
    }
    public static Angle fromAtan2(double x, double y){
        return new Angle(Math.atan2(y, x));
    }
}