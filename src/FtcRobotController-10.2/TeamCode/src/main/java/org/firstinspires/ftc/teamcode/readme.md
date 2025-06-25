## CRABS Team Code

Our codebase follows Dependency Injection (DI), a design pattern for structuring maintainable code. DI is, in my opinion, nothing more than a fancy name for a specific way of structuring maintainable code.

DI has several benefits:
- Code is very well encapsulated and abstracted. This greatly improves readability and understandability.
- Code is easily extensible, as new dependencies can be easily added to add new features.
- Existing behaviour can be easily altered or modified, either temporarily or permanently, by swapping out existing dependencies.

And some drawbacks:
- DI may lead to circular dependencies when two dependencies rely on each other. This could cause the program to hang and fail. You should ensure that there is a clear hierarchy to dependencies, and references are in one direction.
- Implementing DI requires some knowledge of more advanced programming features, like implementing interfaces and arguably generic types.

DI revolves around **registering** specific instances of classes to perform specific roles during runtime. This provides a maintainable way to abstract specific actions and behaviours in two ways:

1. Firstly, any details are nicely encapsulated inside a dependency.

2. Second, a high-level component does not need to worry about the exact implementation of a dependency, except that it exists. This allows for multiple possible ways to implement a dependency.

For example, there can be multiple ways to navigate the robot - such as in the autonomous and TeleOp rounds. The autonomous implementation can control the robot using sensors and timers, whilst the TeleOp implementation can control the robot using gamepad input. 

The classes that perform these specific roles are called **dependencies**, and can be found in the [components](./components/) folder. The behaviour and methods of a dependency are outlined by an accompanying **interface**. For example, [GamePadComponent](./components/GamepadComponent.java) and AutonomousComponent both implement the [ICommandComponent](./components/interfaces/ICommandComponent.java) interface, to gather different inputs and control the robot. At runtime, only one implementing class is registered depending on the OpMode.

All these dependencies are registered in a central dependency store called [RobotComponentStore](./components/RobotComponentStore.java). Two methods, `registerTeleOpComponents(OpMode)` and `registerAutonomousComponents(OpMode)`, can be use to register the different implementations of dependencies at the start of the game. Individual components can also be registered using `registerComponent(Class<T> componentClass, T component)` method, where T is the type of the component that implements A subsidiary of the [IComponent](./components/interfaces/IComponent.java) interface. 

All dependencies implement the [IComponent](./components/interfaces/IComponent.java) interface, which contains `update()` and `updateTelemetry(Telemetry)` methods. These should be called periodically to update all registered dependencies in the main update loop.

A dependency implementation can be retrieved from [RobotComponentStore](./components/RobotComponentStore.java) using `getComponent(Class<T> componentClass)` method. This returns the component that implements the interface T.

```java
// Get the drivetrain component that implements IDrivetrainComponent interface
IDrivetrainComponent drivetrain = RobotComponentStore.getComponent(IDrivetrainComponent.class);

// Do something with that component. In this case, set the turn power to 1.0
drivetrain.setTurnPower(1.0);
```

---

### Similarities to Unity's GameObject System

I think it is worth pointing out that this system is very similar to the GameObject system in Unity, which some of the CRABS developers are familiar with. 

In Unity, a GameObject can have multiple components attached to it. These components can interact with each other and the GameObject itself to control it. In our codebase, we have a robot instead of a GameObject. The components here act similarly to Unity's components, allowing for modular and reusable code.
