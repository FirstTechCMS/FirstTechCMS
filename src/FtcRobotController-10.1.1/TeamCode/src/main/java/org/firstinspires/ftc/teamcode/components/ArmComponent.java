package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.interfaces.IArmComponent;
import org.firstinspires.ftc.teamcode.math.Angle;
import org.firstinspires.ftc.teamcode.wrappers.motors.MotorWrapper;

public class ArmComponent implements IArmComponent {
    private DcMotor leftArmMotor;
    private DcMotor rightArmMotor;

    private Angle targetRotation;
    private boolean stabiliseRotation;
    public ArmComponent(HardwareMap hardwareMap) {
        leftArmMotor = hardwareMap.get(DcMotor.class, "left_arm_motor");
        rightArmMotor = hardwareMap.get(DcMotor.class, "right_arm_motor");

        leftArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setAngle(Angle angle){
        //
    }

    @Override
    public void update() {
        //
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addLine("======== Arm ========");
        telemetry.addData("Left Arm Power", leftArmMotor.getPower());
        telemetry.addData("Right Arm Power", rightArmMotor.getPower());
    }
}
