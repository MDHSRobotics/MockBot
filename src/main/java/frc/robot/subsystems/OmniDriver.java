
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.interactive.OmniDriveArcade;
import frc.robot.consoles.Logger;
import frc.robot.sensors.Gyro;
import frc.robot.sensors.Vision;
import frc.robot.Brain;
import frc.robot.Devices;


// Omni driver subsystem
public class OmniDriver extends Subsystem {

    public enum DriveOrientation {
        ROBOT, FIELD
    }

    // The direction of forward/backward via the controller
    public boolean controlStickDirectionFlipped = false;

    // Motor constants
    private final double SECONDS_FROM_NEUTRAL_TO_FULL = 0;
    private final int TIMEOUT_MS = 10;

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public OmniDriver() {
        Logger.setup("Constructing Subsystem: OmniDriver...");

        // Configure wheel speed controllers
        boolean talonFrontLeftIsConnected = Devices.isConnected(Devices.talonSrxMecWheelFrontLeft);
        boolean talonRearLeftIsConnected = Devices.isConnected(Devices.talonSrxMecWheelRearLeft);
        boolean talonFrontRightIsConnected = Devices.isConnected(Devices.talonSrxMecWheelFrontRight);
        boolean talonRearRightIsConnected = Devices.isConnected(Devices.talonSrxMecWheelRearRight);
        m_talonsAreConnected = (talonFrontLeftIsConnected &&
                                talonRearLeftIsConnected && 
                                talonFrontRightIsConnected && 
                                talonRearRightIsConnected);

        if (!m_talonsAreConnected) {
            Logger.error("OmniDriver talons not all connected! Disabling OmniDriver...");
        }
        else {
            Devices.talonSrxMecWheelFrontLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxMecWheelRearLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxMecWheelFrontRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxMecWheelRearRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        }
    }

    // Initialize Default Command
    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing OmniDriver DefaultCommand -> OmniDriveArcade...");

        setDefaultCommand(new OmniDriveArcade());
    }

    // Flip the control direction of the joystick in Y (or Y Left for Xbox thumbsticks)
    public Boolean flipControlStickDirection() {
        Logger.action("Toggling OmniDriver control stick direction...");

        controlStickDirectionFlipped = !controlStickDirectionFlipped;

        if (controlStickDirectionFlipped) {
            Logger.info("OmniDriver control stick direction is now flipped.");
        }
        else {
            Logger.info("OmniDriver control stick direction is now standard (not flipped).");
        }

        return controlStickDirectionFlipped;
    }


    // Stop all the drive motors
    public void stop() {
        if (!m_talonsAreConnected) {
            Devices.omniDrive.feed();
            return;
        }

        Devices.omniDrive.stopMotor();
    }

    // Drive with just the front two wheels at the given speed
    public void frontWheelDrive(double speed){
        if (!m_talonsAreConnected) {
            Devices.omniDrive.feed();
            return;
        }

        Devices.talonSrxMecWheelFrontLeft.set(speed);
        Devices.talonSrxMecWheelFrontRight.set(speed);
        Devices.talonSrxMecWheelRearLeft.set(0);
        Devices.talonSrxMecWheelRearRight.set(0);
        Devices.omniDrive.feed();
    }

    // Drive straight at the given speed
    public void driveStraight(double speed, double rotation) {
        if (!m_talonsAreConnected) {
            Devices.omniDrive.feed();
            return;
        }

        Devices.omniDrive.arcadeDrive(speed, rotation, false);
    }

    // Rotate at the given speed
    public void rotate(double speed, double rotation) {
        if (!m_talonsAreConnected) {
            Devices.omniDrive.feed();
            return;
        }

        Devices.omniDrive.arcadeDrive(speed, rotation, false);
    }

    public void arcadeDrive(double speed, double rotation, boolean squareInputs) {
        arcadeDrive(speed, rotation, squareInputs);
    }

}
