package org.firstinspires.ftc.teamcode.components.interfaces;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * A component is a small encapsulation of behaviour for the robot, that interacts with specific, related hardware.
 * It is used as part of a Dependency Injection pattern, where IComponents are dependencies.
 */
public interface IComponent {
    void update();

    void updateTelemetry(Telemetry telemetry);
}