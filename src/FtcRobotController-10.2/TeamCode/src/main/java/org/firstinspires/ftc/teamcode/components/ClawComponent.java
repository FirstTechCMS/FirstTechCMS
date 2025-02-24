package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.IClawComponent;

/** Implementation of the component that manages the claw servos */
public class ClawComponent implements IClawComponent {
    private final Servo leftServo;
    private final Servo rightServo;
    private static final double servoPositionA = 1;
    private static final double servoPositionB = 0;
    private boolean isOpen;

    public ClawComponent(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class, "left_claw_servo");
        rightServo = hardwareMap.get(Servo.class, "right_claw_servo");
    }

    @Override
    public void open() {
        leftServo.setPosition(servoPositionB);
        rightServo.setPosition(servoPositionA);
        isOpen = true;
    }

    @Override
    public void close() {
        leftServo.setPosition(servoPositionA);
        rightServo.setPosition(servoPositionB);
        isOpen = false;
    }

    @Override
    public boolean getIsOpen() {
        return isOpen;
    }

    @Override
    public void update() { }

    @Override
    public void toggle() {
        if (isOpen)
            close();
        else
            open();
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData("Claw Target", isOpen ? "Open" : "Closed");
    }
}
