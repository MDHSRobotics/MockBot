
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.interactive.OmniDriveArcade;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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

    //define the omniDrive
    public static DifferentialDrive omniDrive = null;

    public OmniDriver() {
        Logger.setup("Constructing Subsystem: OmniDriver...");

        Devices.talonSrxOmniWheelRearLeft.follow(Devices.talonSrxOmniWheelFrontLeft);
        Devices.talonSrxOmniWheelRearRight.follow(Devices.talonSrxOmniWheelFrontRight);
        Devices.talonSrxOmniWheelRear.follow(Devices.talonSrxOmniWheelFront);

        omniDrive = new DifferentialDrive(Devices.talonSrxOmniWheelFrontLeft,
                                          Devices.talonSrxOmniWheelFrontRight);
       
        // Configure wheel speed controllers
        boolean talonFrontLeftIsConnected = Devices.isConnected(Devices.talonSrxOmniWheelFrontLeft);
        boolean talonRearLeftIsConnected = Devices.isConnected(Devices.talonSrxOmniWheelRearLeft);
        boolean talonFrontRightIsConnected = Devices.isConnected(Devices.talonSrxOmniWheelFrontRight);
        boolean talonRearRightIsConnected = Devices.isConnected(Devices.talonSrxOmniWheelRearRight);
        boolean talonFrontIsConnected = Devices.isConnected(Devices.talonSrxOmniWheelFront);
        boolean talonRearIsConnected = Devices.isConnected(Devices.talonSrxOmniWheelRear);
        m_talonsAreConnected = (talonFrontLeftIsConnected &&
                                talonRearLeftIsConnected && 
                                talonFrontRightIsConnected && 
                                talonRearRightIsConnected &&
                                talonFrontIsConnected &&
                                talonRearIsConnected);

        if (!m_talonsAreConnected) {
            Logger.error("OmniDriver talons not all connected! Disabling OmniDriver...");
        }
        else {
            Devices.talonSrxOmniWheelFrontLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxOmniWheelRearLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxOmniWheelFrontRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxOmniWheelRearRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxOmniWheelFront.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxOmniWheelRear.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
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
            omniDrive.feed();
            return;
        }

        omniDrive.stopMotor();
    }

    // Drive straight at the given speed
    public void driveStraight(double speed) {
        if (!m_talonsAreConnected) {
            omniDrive.feed();
            return;
        }

        omniDrive.arcadeDrive(speed, 0, false);
    }

    // Rotate at the given speed
    public void rotate(double rotation) {
        if (!m_talonsAreConnected) {
            omniDrive.feed();
            return;
        }

        omniDrive.arcadeDrive(0, rotation, false);
    }

    public void arcadeDrive(double speed, double rotation, boolean squareInputs, double strafe) {
        omniDrive.arcadeDrive(speed, rotation, squareInputs);

        Devices.talonSrxOmniWheelFront.set(strafe);
    }

}
