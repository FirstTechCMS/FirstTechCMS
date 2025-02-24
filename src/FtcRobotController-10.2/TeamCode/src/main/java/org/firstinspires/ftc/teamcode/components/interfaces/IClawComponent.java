package org.firstinspires.ftc.teamcode.components.interfaces;

/** Interface for the component that manages the claw servos. */
public interface IClawComponent extends IComponent {
    void open();
    void close();
    void toggle();
    boolean getIsOpen();
}
