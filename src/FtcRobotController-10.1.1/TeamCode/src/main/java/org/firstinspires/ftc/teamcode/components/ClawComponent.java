package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.IClawComponent;

/** Implementation of the component that manages the claw servos */
public class ClawComponent implements IClawComponent {
    private final Servo leftServo;
    private final Servo rightServo;
    private boolean isOpen;
    private static final double openPosition = 1;
    private static final double closedPosition = 0;

    public ClawComponent(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class, "left_claw_servo");
        rightServo = hardwareMap.get(Servo.class, "right_claw_servo");
    }

    @Override
    public void open() {
        leftServo.setPosition(closedPosition);
        rightServo.setPosition(openPosition);
        isOpen = true;
    }

    @Override
    public void close() {
        leftServo.setPosition(openPosition);
        rightServo.setPosition(closedPosition);
        isOpen = false;
    }
    public boolean getIsOpen() {
        return isOpen;
    }

    @Override
    public void update() {

    }

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
