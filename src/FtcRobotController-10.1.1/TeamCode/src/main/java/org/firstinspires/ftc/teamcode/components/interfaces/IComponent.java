package org.firstinspires.ftc.teamcode.modules.interfaces;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface IComponent {
    void update();
    void updateTelemetry(Telemetry telemetry);
}