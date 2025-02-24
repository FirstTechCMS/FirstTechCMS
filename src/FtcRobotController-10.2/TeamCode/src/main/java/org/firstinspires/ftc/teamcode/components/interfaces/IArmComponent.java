package org.firstinspires.ftc.teamcode.components.interfaces;

import org.firstinspires.ftc.teamcode.math.Angle;

/** Interface for the component that manages the arm motors. */
public interface IArmComponent extends IComponent {
    void setAngle(Angle angle);
    Angle getAngle();
    void shiftAngle(Angle amount);
}
