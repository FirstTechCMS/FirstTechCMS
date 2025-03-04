package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.*;
import org.firstinspires.ftc.teamcode.enums.OpModeId;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Collection of all the active components in the robot.</p>
 * <p>Follows a Dependency Injection (DI) pattern, where <code>RobotComponentStore</code> is the injector.
 * DI is a pattern to store and managed frequently-used implementations of interfaces, where the exact implementation of the interfaces can change.</p>
 * <p>For example, the implementation of the interface that commands the robot will change for the Autonomous and TeleOp rounds.
 * DI allows for a highly modular approach that is very maintainable and extensible.</p>
 * <p>Implementations are "registered" by their implemented interface. When that implementation of the component is needed, the interface name is passed to a get method that returns the requested implementation.</p>
 * */
public class RobotComponentStore {
    private static final Map<Class<? extends IComponent>, IComponent> components = new HashMap<>();

    /**
     * Register a component to the collection
     * @param componentClass The type to register the instance to.
     * @param instance The instance to register.
     * @param <T> The type of the class to register.
     */
    public static <T extends IComponent> void registerComponent(Class<T> componentClass, T instance) {
        components.put(componentClass, instance);
    }

    /**
     * Gets a registered instance of a component.
     * @param componentClass The type the instance is registered to.
     * @return The requested implementation of the component type.
     * @param <T> The type of the component.
     */
    public static <T extends IComponent> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }

    private static void registerBothModes(OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;

        DrivetrainComponent drivetrainComponent = new DrivetrainComponent(hardwareMap);
        registerComponent(IDrivetrainComponent.class, drivetrainComponent);

        ArmComponent armComponent = new ArmComponent(hardwareMap);
        registerComponent(IArmComponent.class, armComponent);

        SensorComponent sensorComponent = new SensorComponent(hardwareMap);
        registerComponent(ISensorComponent.class, sensorComponent);

        ClawComponent clawComponent = new ClawComponent(hardwareMap);
        registerComponent(IClawComponent.class, clawComponent);
    }

    public static void registerOpModeComponents(OpModeId opModeId, OpMode opMode) {
        registerBothModes(opMode);
        switch (opModeId) {
            case TELEOP:
                registerTeleOpComponents(opMode);
                break;
            case AUTONOMOUS:
                registerAutonomousComponents(opMode);
                break;
        }
    }

    /**
     * Register components needed specifically for TeleOp mode.
     * @param opMode The instance of the current OpMode.
     */
    private static void registerTeleOpComponents(OpMode opMode) {
        GamepadComponent gamepadComponent = new GamepadComponent(opMode.gamepad1, opMode.gamepad2);
        registerComponent(ICommandComponent.class, gamepadComponent);
    }

    /**
     * Register components needed specifically for Autonomous mode.
     * @param opMode The instance of the current OpMode.
     */
    private static void registerAutonomousComponents(OpMode opMode) {
        // Nothing yet to register
    }

    /**
     * Update all registered components.
     */
    public static void update() {
        for (IComponent component : components.values()) {
            component.update();
        }
    }

    /**
     * Call the "updateTelemetry" method on all components.
     * @param telemetry
     */
    public static void updateTelemetry(Telemetry telemetry) {
        for (IComponent component : components.values()) {
            component.updateTelemetry(telemetry);
        }
    }
}