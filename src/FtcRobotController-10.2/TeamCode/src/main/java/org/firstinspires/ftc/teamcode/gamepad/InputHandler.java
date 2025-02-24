package org.firstinspires.ftc.teamcode.gamepad;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

/** Wrapper for a Gamepad. */
public class InputHandler {
    private final Gamepad gamepad;
    private final HashMap<String, BooleanHistory> keyStates;
    private static final String[] defaultButtons = new String[]{
            "a", "b", "x", "y", "dpad_up", "dpad_down", "dpad_left", "dpad_right"
    };

    public InputHandler(Gamepad gamepad) {
        this.gamepad = gamepad;
        keyStates = new HashMap<>();
        for (String button : defaultButtons) {
            keyStates.put(button, new BooleanHistory());
        }
    }

    public double GetLeftStickX() {
        return gamepad.left_stick_x;
    }
    public double GetLeftStickY() {
        return gamepad.left_stick_y;
    }

    public double GetRightStickX() {
        return gamepad.right_stick_x;
    }

    public double GetRightStickY() {
        return gamepad.right_stick_y;
    }

    public boolean GetKey(String key) {
        return Objects.requireNonNull(keyStates.get(key)).GetCurrent();
    }

    public boolean GetKeyDown(String key) {
        return Objects.requireNonNull(keyStates.get(key)).GetIsValueUp();
    }

    public void Update() {
        Objects.requireNonNull(keyStates.get("a")).Update(gamepad.a);
        Objects.requireNonNull(keyStates.get("b")).Update(gamepad.b);
        Objects.requireNonNull(keyStates.get("x")).Update(gamepad.x);
        Objects.requireNonNull(keyStates.get("y")).Update(gamepad.y);
        Objects.requireNonNull(keyStates.get("dpad_up")).Update(gamepad.dpad_up);
        Objects.requireNonNull(keyStates.get("dpad_down")).Update(gamepad.dpad_down);
        Objects.requireNonNull(keyStates.get("dpad_left")).Update(gamepad.dpad_left);
        Objects.requireNonNull(keyStates.get("dpad_right")).Update(gamepad.dpad_right);
    }
}
