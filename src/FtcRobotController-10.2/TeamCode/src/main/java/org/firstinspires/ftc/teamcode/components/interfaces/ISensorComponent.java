package org.firstinspires.ftc.teamcode.components.interfaces;

import org.firstinspires.ftc.teamcode.components.interfaces.IComponent;
import org.firstinspires.ftc.teamcode.math.Angle;

/**
 * Interface for the component that manages sensors.
 * */
public interface ISensorComponent extends IComponent {
    Angle getHeading();
    double getDistanceBack();
}
