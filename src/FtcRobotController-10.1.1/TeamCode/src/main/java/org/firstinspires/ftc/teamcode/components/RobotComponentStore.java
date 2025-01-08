package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.components.interfaces.*;

import java.util.HashMap;
import java.util.Map;

public class RobotComponentStore {
    private static final Map<Class<? extends IComponent>, IComponent> components = new HashMap<>();

    public static <T extends IComponent> void registerComponent(Class<T> componentClass, T instance) {
        components.put(componentClass, instance);
    }
    public static <T extends IComponent> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }
    public static void registerTeleOpMode(HardwareMap hardwareMap){
        DrivetrainComponent drivetrainComponent = new DrivetrainComponent(hardwareMap);
        registerComponent(IDrivetrainComponent.class, drivetrainComponent);
    }
}