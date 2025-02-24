package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.IArmComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.IClawComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.ICommandComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;
import org.firstinspires.ftc.teamcode.gamepad.InputHandler;
import org.firstinspires.ftc.teamcode.math.Angle;

/**
 * Implementation of the component that responds to gamepad inputs and controls other components.
 * */
public class GamepadComponent implements ICommandComponent {
    private final InputHandler driverInput;
    private final InputHandler armClawInput;
    private static final float ARM_MULTIPLIER = 0.05f;

    public GamepadComponent(Gamepad gamepad1, Gamepad gamepad2) {
        this.driverInput = new InputHandler(gamepad1);
        this.armClawInput = new InputHandler(gamepad2);
    }

    @Override
    public void update() {
        driverInput.Update();
        armClawInput.Update();

        IDrivetrainComponent drivetrainComponent = RobotComponentStore.getComponent(IDrivetrainComponent.class);

        double horizontalDrive = driverInput.GetLeftStickX();
        double verticalDrive = -driverInput.GetLeftStickY();

        double angularVelocity = driverInput.GetRightStickX();

        double power = Math.hypot(horizontalDrive, verticalDrive);
        Angle angle = Angle.fromAtan2(verticalDrive, horizontalDrive);

        drivetrainComponent.setMecanumDrive(angle, power);
        drivetrainComponent.setTurnPower(angularVelocity);

        double armPower = -armClawInput.GetLeftStickY() * ARM_MULTIPLIER;
        IArmComponent armComponent = RobotComponentStore.getComponent(IArmComponent.class);
        armComponent.shiftAngle(Angle.fromRadians(armPower));

        if (armClawInput.GetKeyDown("a")){
            IClawComponent clawComponent = RobotComponentStore.getComponent(IClawComponent.class);
            clawComponent.toggle();
        }
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addLine("======== Gamepad ========");
        telemetry.addData("1_Left", "X: %.2f, Y: %.2f", driverInput.GetLeftStickX(), -driverInput.GetLeftStickY());
        telemetry.addData("1_Right", "X: %.2f, Y: %.2f", driverInput.GetRightStickX(), -driverInput.GetRightStickY());
        telemetry.addData("2_Left", "X: %.2f, Y: %.2f", armClawInput.GetLeftStickX(), -armClawInput.GetLeftStickY());
        telemetry.addData("2_Right", "X: %.2f, Y: %.2f", armClawInput.GetRightStickX(), -armClawInput.GetRightStickY());
    }
}
