package org.firstinspires.ftc.teamcode.gamepad;

/** Wrapper to stores the current and previous state of a boolean value. Useful for responding to changes. */
public class BooleanHistory {
    private boolean currentValue;
    private boolean lastValue;
    public void Update(boolean newState) {
        lastValue = currentValue;
        currentValue = newState;
    }

    public boolean GetCurrent(){
        return currentValue;
    }

    public boolean GetIsChanged() {
        return lastValue != currentValue;
    }

    public boolean GetIsValueUp() {
        return !lastValue && currentValue;
    }

    public boolean GetIsValueDown() {
        return lastValue && !currentValue;
    }
}
