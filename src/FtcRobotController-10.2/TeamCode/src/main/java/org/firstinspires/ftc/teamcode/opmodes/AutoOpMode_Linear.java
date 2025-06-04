package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.RobotComponentStore;
import org.firstinspires.ftc.teamcode.components.interfaces.IArmComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.IDrivetrainComponent;
import org.firstinspires.ftc.teamcode.components.interfaces.ISensorComponent;
import org.firstinspires.ftc.teamcode.enums.OpModeId;
import org.firstinspires.ftc.teamcode.math.Angle;

import java.util.Timer;
import java.util.TimerTask;

@Autonomous(name = "Auto OpMode", group = "Linear OpMode")
public class AutoOpMode_Linear extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        RobotComponentStore.registerOpModeComponents(OpModeId.AUTONOMOUS, this);

        waitForStart();
        timeOut(30000);

        IDrivetrainComponent drivetrain = RobotComponentStore.getComponent(IDrivetrainComponent.class);
        IArmComponent armComponent = RobotComponentStore.getComponent(IArmComponent.class);
        ISensorComponent sensorComponent = RobotComponentStore.getComponent(ISensorComponent.class);

        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < 20000) {
            //Back up slightly
            drivetrain.setMecanumDrive(Angle.fromDegrees(0), -0.2);
            sleep(1000);
            drivetrain.setMecanumDrive(Angle.fromDegrees(0), 0);

            // Turns 90 degrees right
            drivetrain.setTurnPower(0.5);
            while (sensorComponent.getHeading().radians() < Angle.fromDegrees(90).radians()) {
                RobotComponentStore.update();
            }
            drivetrain.setTurnPower(0);

            // Moves forward until it is in the middle
            drivetrain.setMecanumDrive(Angle.fromDegrees(0), 0.2);
            while (sensorComponent.getDistanceBack() < 161) {
                RobotComponentStore.update();
                RobotComponentStore.updateTelemetry(telemetry);
                sleep(100);
            }
            drivetrain.setMecanumDrive(Angle.fromDegrees(0), 0);

            // Turns toward submersible
            drivetrain.setTurnPower(0.5);
            while (sensorComponent.getHeading().radians() < Angle.fromDegrees(180).radians()) {
                RobotComponentStore.update();
            }
            drivetrain.setTurnPower(0);

            // Move forward until it is right next to submersible
            drivetrain.setMecanumDrive(Angle.fromDegrees(0), 0.2);
            while (sensorComponent.getDistanceBack() < 75) {
                RobotComponentStore.update();
                RobotComponentStore.updateTelemetry(telemetry);
                sleep(100);
            }
            drivetrain.setMecanumDrive(Angle.fromDegrees(0), 0);
        }

        RobotComponentStore.update();
        RobotComponentStore.updateTelemetry(telemetry);

        telemetry.update();
    }

    public void timeOut(int time) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                telemetry.addLine("Time limit reached, exiting Opmode");
                System.exit(0);
            }
        }, time);
    }

}
