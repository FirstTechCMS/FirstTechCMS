package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.IArmComponent;
import org.firstinspires.ftc.teamcode.wrappers.motors.MotorWrapper;

public class ArmComponent implements IArmComponent {
    private MotorWrapper leftArmMotor;
    private MotorWrapper rightArmMotor;
    public ArmComponent(HardwareMap hardwareMap) {
        leftArmMotor = new MotorWrapper(hardwareMap, "left_arm_motor");
        rightArmMotor = new MotorWrapper(hardwareMap, "right_arm_motor", DcMotorSimple.Direction.REVERSE);
    }

    public void setPower(double power) {
        leftArmMotor.setPower(power);
        rightArmMotor.setPower(power);
    }

    @Override
    public void update() {

    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addLine("======== Arm ========");
        telemetry.addData("Left Arm Power", leftArmMotor.getPower());
        telemetry.addData("Right Arm Power", rightArmMotor.getPower());
    }
}
