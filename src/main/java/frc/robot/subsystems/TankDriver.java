
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Devices;
import frc.robot.commands.interactive.TankDrive;
import frc.robot.consoles.Logger;


// Mecanum driver subsystem
public class TankDriver extends Subsystem {

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

    public TankDriver() {
        Logger.setup("Constructing Subsystem: TankDriver...");

        // Configure wheel speed controllers
        boolean talonFrontLeftIsConnected = Devices.isConnected(Devices.talonSrxTankWheelFrontLeft);
        boolean talonRearLeftIsConnected = Devices.isConnected(Devices.talonSrxTankWheelRearLeft);
        boolean talonFrontRightIsConnected = Devices.isConnected(Devices.talonSrxTankWheelFrontRight);
        boolean talonRearRightIsConnected = Devices.isConnected(Devices.talonSrxTankWheelRearRight);
        m_talonsAreConnected = (talonFrontLeftIsConnected &&
                                talonRearLeftIsConnected && 
                                talonFrontRightIsConnected && 
                                talonRearRightIsConnected);

        if (!m_talonsAreConnected) {
            Logger.error("TankDriver talons not all connected! Disabling TankDriver...");
        }
        else {
            Devices.talonSrxTankWheelFrontLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxTankWheelRearLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxTankWheelFrontRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxTankWheelRearRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        }
    }

    // Initialize Default Command
    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing TankDriver DefaultCommand -> TankMovement...");

        setDefaultCommand(new TankDrive());
    }

    // Flip the control direction of the joystick in Y (or Y Left for Xbox thumbsticks)
    public Boolean flipControlStickDirection() {
        Logger.action("Toggling TankDriver control stick direction...");

        controlStickDirectionFlipped = !controlStickDirectionFlipped;

        if (controlStickDirectionFlipped) {
            Logger.info("TankDriver control stick direction is now flipped.");
        }
        else {
            Logger.info("TankDriver control stick direction is now standard (not flipped).");
        }

        return controlStickDirectionFlipped;
    }


    // Stop all the drive motors
    public void stop() {
        if (!m_talonsAreConnected) {
            Devices.tankDrive.feed();
            return;
        }

        Devices.tankDrive.stopMotor();
    }

    // // Drive straight at the given speed
    // public void driveStraight(double speed) {
    //     if (!m_talonsAreConnected) {
    //         Devices.mecDrive.feed();
    //         return;
    //     }

    //     Devices.mecDrive.driveCartesian(0, speed, 0);
    // }

    // Drive using the cartesian method, using the current control orientation
    // public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
    //     DriveOrientation orientation = Brain.getDriveOrientation();
    //     driveCartesian(ySpeed, xSpeed, zRotation, orientation);
    // }

    // Drive using the tank method, using the given control orientation
    public void driveTank(double yLeft, double yRight) {
        if (!m_talonsAreConnected) {
            Devices.tankDrive.feed();
            return;
        }
        Devices.tankDrive.tankDrive(yLeft, yRight, false);
    }

    // Drive to align the Robot to a detected line at the given yaw
    // public void driveAlign(double targetYaw) {
    //     Logger.setup("##");

    //     // Get the correction yaw needed to align the Robot with the target yaw
    //     double yaw = Devices.gyro.getYaw();
    //     double correction = targetYaw - yaw;
    //     if (correction > 180) correction = correction - 360;
    //     if (correction < -180) correction = correction + 360;
    //     Logger.info("MecDriver -> Gyro -> Target Yaw: " + targetYaw + "; Current Yaw: " + yaw + "; Correction: " + correction);

    //     // Get the drive polar magnitude and angle needed to align the Robot's center with the appropriate detected line
    //     double magnitude = 0;
    //     double angle = 0;
    //     if (-45 <= correction && correction <= 45) {
    //         // Our target in in front of us, so look for a line in front to use to start centering
    //         boolean detected = Vision.frontLineDetected();
    //         if (detected) {
    //             boolean centered = Vision.isFrontCentered();
    //             if (!centered) {
    //                 double imageX = Vision.getFrontCorrectedX();
    //                 angle = correction + 90;
    //                 magnitude = Brain.getAlignFrontMagnitude();
    //                 if (imageX < 0) magnitude = -magnitude;
    //                 Logger.info("MecDriver -> Front Camera -> Pixels to correct: " + imageX + "; Magnitude: " + magnitude + "; Angle: " + angle);
    //             }
    //         }
    //     }
    //     else if (-135 < correction && correction < -45) {
    //         // Our target is to the left, so look for a line to the left use to start centering
    //         boolean detected = Vision.leftLineDetected();
    //         if (detected) {
    //             boolean centered = Vision.isLeftCentered();
    //             if (!centered) {
    //                 double imageX = Vision.getLeftCorrectedX();
    //                 angle = correction + 90;
    //                 magnitude = Brain.getAlignSideMagnitude();
    //                 if (imageX < 0) magnitude = -magnitude;
    //                 Logger.info("MecDriver -> Left Camera -> Pixels to correct: " + imageX + "; Magnitude: " + magnitude + "; Angle: " + angle);
    //             }
    //         }
    //     }
    //     else if (45 < correction && correction < 135) {
    //         // Our target is to the right, so look for a line to the right use to start centering
    //         boolean detected = Vision.rightLineDetected();
    //         if (detected) {
    //             boolean centered = Vision.isRightCentered();
    //             if (!centered) {
    //                 double imageX = Vision.getRightCorrectedX();
    //                 angle = correction + 90;
    //                 magnitude = Brain.getAlignSideMagnitude();
    //                 if (imageX > 0) magnitude = -magnitude;
    //                 Logger.info("MecDriver -> Right Camera -> Pixels to correct: " + imageX + "; Magnitude: " + magnitude + "; Angle: " + angle);
    //             }
    //         }
    //     }
    // }

}
