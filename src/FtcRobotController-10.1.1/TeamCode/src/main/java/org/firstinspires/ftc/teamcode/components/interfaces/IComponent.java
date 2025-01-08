package org.firstinspires.ftc.teamcode.components.interfaces;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface IComponent {
    void update();
    void updateTelemetry(Telemetry telemetry);
}