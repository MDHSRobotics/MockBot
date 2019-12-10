
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.interactive.MecDriveCartesian;
import frc.robot.consoles.Logger;
import frc.robot.sensors.Gyro;
import frc.robot.sensors.Vision;
import frc.robot.Brain;
import frc.robot.Devices;

//pathweaver imports
import edu.wpi.first.wpilibj.Notifier;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import java.io.IOException;

// Mecanum driver subsystem
public class MecDriver extends Subsystem {

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

    //PathWeaver constants
    private static final int k_ticks_per_rev = 1024; //encoder ticks per wheel revolution
    private static final double k_wheel_diameter = 6; //diameter of wheels
    private static final double k_max_velocity = 10; //max velocity, ft/s

    private EncoderFollower m_left_follower;
    private EncoderFollower m_right_follower;

    public static Notifier m_follower_notifier;
    
    private static final String k_path_name_left = "/home/lvuser/deploy/paths/place cargo.left.pf1.csv"; //folder on roboRIO
    private static final String k_path_name_right = "/home/lvuser/deploy/paths/place cargo.right.pf1.csv"; //folder on roboRIO

    public MecDriver() {
        Logger.setup("Constructing Subsystem: MecDriver...");

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
            Logger.error("MecDriver talons not all connected! Disabling MecDriver...");
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
        Logger.setup("Initializing MecDriver DefaultCommand -> MecDriveCartesian...");

        setDefaultCommand(new MecDriveCartesian());
    }

    // Flip the control direction of the joystick in Y (or Y Left for Xbox thumbsticks)
    public Boolean flipControlStickDirection() {
        Logger.action("Toggling MecDriver control stick direction...");

        controlStickDirectionFlipped = !controlStickDirectionFlipped;

        if (controlStickDirectionFlipped) {
            Logger.info("MecDriver control stick direction is now flipped.");
        }
        else {
            Logger.info("MecDriver control stick direction is now standard (not flipped).");
        }

        return controlStickDirectionFlipped;
    }

    // Toggle the drive orientation for the mecanum drive
    public DriveOrientation toggleDriveOrientation() {
        Logger.action("Toggling MecDriver drive orientation...");

        DriveOrientation orientation = Brain.getDriveOrientation();
        if (orientation == DriveOrientation.FIELD) {
            orientation = DriveOrientation.ROBOT;
            Logger.info("MecDriver drive orientation is now ROBOT.");
        }
        else if (orientation == DriveOrientation.ROBOT) {
            orientation = DriveOrientation.FIELD;
            Logger.info("MecDriver drive orientation is now FIELD.");
        }
        Brain.setDriveOrientation(orientation);

        return orientation;
    }

    // Stop all the drive motors
    public void stop() {
        if (!m_talonsAreConnected) {
            Devices.mecDrive.feed();
            return;
        }

        Devices.mecDrive.stopMotor();
    }

    // Drive with just the front two wheels at the given speed
    public void frontWheelDrive(double speed){
        if (!m_talonsAreConnected) {
            Devices.mecDrive.feed();
            return;
        }

        Devices.talonSrxMecWheelFrontLeft.set(speed);
        Devices.talonSrxMecWheelFrontRight.set(speed);
        Devices.talonSrxMecWheelRearLeft.set(0);
        Devices.talonSrxMecWheelRearRight.set(0);
        Devices.mecDrive.feed();
    }

    // Strafe at the given speed
    public void strafe(double speed) {
        if (!m_talonsAreConnected) {
            Devices.mecDrive.feed();
            return;
        }

        Devices.mecDrive.driveCartesian(speed, 0, 0);
    }

    // Drive straight at the given speed
    public void driveStraight(double speed) {
        if (!m_talonsAreConnected) {
            Devices.mecDrive.feed();
            return;
        }

        Devices.mecDrive.driveCartesian(0, speed, 0);
    }

    // Rotate at the given speed
    public void rotate(double speed) {
        if (!m_talonsAreConnected) {
            Devices.mecDrive.feed();
            return;
        }

        Devices.mecDrive.driveCartesian(0, 0, speed);
    }

    // Orbit at the given speed, with the robot always looking inward
    // Positive is clockwise, negative is counter-clockwise
    public void orbitInward(double magnitude, double zRotation) {
        if (!m_talonsAreConnected) {
            Devices.mecDrive.feed();
            return;
        }

        // TODO: Test that this does what the method description says it does
        Devices.mecDrive.drivePolar(magnitude, -90, zRotation);
    }

    // Orbit at the given speed, with the robot always looking outward
    public void orbitOutward(double magnitude, double zRotation) {
        if (!m_talonsAreConnected) {
            Devices.mecDrive.feed();
            return;
        }

        // TODO: Test that this does what the method description says it does
        Devices.mecDrive.drivePolar(magnitude, 90, zRotation);
    }

    // Drive using the cartesian method, using the current control orientation
    public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
        DriveOrientation orientation = Brain.getDriveOrientation();
        driveCartesian(ySpeed, xSpeed, zRotation, orientation);
    }

    // Drive using the cartesian method, using the given control orientation
    public void driveCartesian(double ySpeed, double xSpeed, double zRotation, DriveOrientation orientation) {
        if (!m_talonsAreConnected) {
            Devices.mecDrive.feed();
            return;
        }

        if (orientation == DriveOrientation.ROBOT) {
            // Logger.info("Cartesian Movement: " + ySpeed + ", " + xSpeed + ", " + zRotation);
            Devices.mecDrive.driveCartesian(ySpeed, xSpeed, zRotation);
        }
        else if (orientation == DriveOrientation.FIELD) {
            double gyroAngle = Devices.gyro.getYaw();
            // Logger.info("Cartesian Movement: " + ySpeed + ", " + xSpeed + ", " + zRotation + ", " + gyroAngle);
            Devices.mecDrive.driveCartesian(ySpeed, xSpeed, zRotation, gyroAngle);
        }
    }

    // Drive using the polar method
    public void drivePolar(double magnitude, double angle, double rotation) {
        if (!m_talonsAreConnected) {
            Devices.mecDrive.feed();
            return;
        }

        // Logger.info("Polar Movement: " + magnitude + ", " + angle + ", " + rotation);
        Devices.mecDrive.drivePolar(magnitude, angle, rotation);
    }

    public void autonomousInit() throws IOException { //TODO: error when "throws IOException" is not included

        //PathWeaver v2019.3.06 = right and left swapped, will be fixed in v2019.3.1
        Trajectory left_trajectory = PathfinderFRC.getTrajectory(k_path_name_left + ".right");
        Trajectory right_trajectory = PathfinderFRC.getTrajectory(k_path_name_right + ".left");

        m_left_follower = new EncoderFollower(left_trajectory);
        m_right_follower = new EncoderFollower(right_trajectory);

        m_left_follower.configureEncoder(Devices.talonSrxMecWheelFrontLeft.getSelectedSensorPosition(), k_ticks_per_rev, k_wheel_diameter);
        // Tune the PID values on the following line
        m_left_follower.configurePIDVA(1.0, 0.0, 0.0, 1 / k_max_velocity, 0);

        m_right_follower.configureEncoder(Devices.talonSrxMecWheelFrontRight.getSelectedSensorPosition(), k_ticks_per_rev, k_wheel_diameter);
        // Tune the PID values on the following line
        m_right_follower.configurePIDVA(1.0, 0.0, 0.0, 1 / k_max_velocity, 0);
    
        m_follower_notifier = new Notifier(this::followPath);
        m_follower_notifier.startPeriodic(left_trajectory.get(0).dt);

    }

    private void followPath() {
        if (m_left_follower.isFinished() || m_right_follower.isFinished()) {
          m_follower_notifier.stop();
        } 
        
        else {
          double left_speed = m_left_follower.calculate(Devices.talonSrxMecWheelFrontLeft.getSelectedSensorPosition());
          double right_speed = m_right_follower.calculate(Devices.talonSrxMecWheelFrontRight.getSelectedSensorPosition());
          double heading = Devices.gyro.getAngle();
          double desired_heading = Pathfinder.r2d(m_left_follower.getHeading());
          double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
          double turn =  0.8 * (-1.0/80.0) * heading_difference;
          Devices.talonSrxMecWheelFrontLeft.set(left_speed + turn);
          Devices.talonSrxMecWheelFrontRight.set(right_speed - turn);
        }
      }
    
    // Drive to align the Robot to a detected line at the given yaw
    public void driveAlign(double targetYaw) {
        Logger.setup("##");

        // Get the correction yaw needed to align the Robot with the target yaw
        double yaw = Devices.gyro.getYaw();
        double correction = targetYaw - yaw;
        if (correction > 180) correction = correction - 360;
        if (correction < -180) correction = correction + 360;
        Logger.info("MecDriver -> Gyro -> Target Yaw: " + targetYaw + "; Current Yaw: " + yaw + "; Correction: " + correction);

        // Get the drive polar magnitude and angle needed to align the Robot's center with the appropriate detected line
        double magnitude = 0;
        double angle = 0;
        if (-45 <= correction && correction <= 45) {
            // Our target in in front of us, so look for a line in front to use to start centering
            boolean detected = Vision.frontLineDetected();
            if (detected) {
                boolean centered = Vision.isFrontCentered();
                if (!centered) {
                    double imageX = Vision.getFrontCorrectedX();
                    angle = correction + 90;
                    magnitude = Brain.getAlignFrontMagnitude();
                    if (imageX < 0) magnitude = -magnitude;
                    Logger.info("MecDriver -> Front Camera -> Pixels to correct: " + imageX + "; Magnitude: " + magnitude + "; Angle: " + angle);
                }
            }
        }
        else if (-135 < correction && correction < -45) {
            // Our target is to the left, so look for a line to the left use to start centering
            boolean detected = Vision.leftLineDetected();
            if (detected) {
                boolean centered = Vision.isLeftCentered();
                if (!centered) {
                    double imageX = Vision.getLeftCorrectedX();
                    angle = correction + 90;
                    magnitude = Brain.getAlignSideMagnitude();
                    if (imageX < 0) magnitude = -magnitude;
                    Logger.info("MecDriver -> Left Camera -> Pixels to correct: " + imageX + "; Magnitude: " + magnitude + "; Angle: " + angle);
                }
            }
        }
        else if (45 < correction && correction < 135) {
            // Our target is to the right, so look for a line to the right use to start centering
            boolean detected = Vision.rightLineDetected();
            if (detected) {
                boolean centered = Vision.isRightCentered();
                if (!centered) {
                    double imageX = Vision.getRightCorrectedX();
                    angle = correction + 90;
                    magnitude = Brain.getAlignSideMagnitude();
                    if (imageX > 0) magnitude = -magnitude;
                    Logger.info("MecDriver -> Right Camera -> Pixels to correct: " + imageX + "; Magnitude: " + magnitude + "; Angle: " + angle);
                }
            }
        }

        // Get the rotation speed to align the Robot with the target gyro yaw
        double zRotation = (correction / 180) * Brain.getAlignZSensitivity();
        boolean isCloseEnough = Math.abs(correction) < Brain.getAlignZTolerance();
        if (!isCloseEnough) {
            if (0 < zRotation && zRotation < Brain.getAlignZSpeedMinimum()) zRotation = Brain.getAlignZSpeedMinimum();
            if (0 > zRotation && zRotation > -Brain.getAlignZSpeedMinimum()) zRotation = -Brain.getAlignZSpeedMinimum();
        }

        Logger.action("MecDriver -> Drive Polar: " + magnitude + ", " + angle + ", " + zRotation);
        if (!m_talonsAreConnected) {
            Devices.mecDrive.feed();
        }
        else {
            // TODO: Need to test this, to balance the speeds to produce the fastest and most reliable simultaneous alignment
            Devices.mecDrive.drivePolar(magnitude, angle, zRotation);
        }
        Logger.ending("^^");
    }

    // TODO: Use this to indicate to the driver that the Robot is aligned with the target (lights? Shuffleboard?)
    public boolean isAligned(double targetAngle) {
        boolean straight = Gyro.isYawAligned(targetAngle);
        if (!straight) return false;

        boolean detected = Vision.frontLineDetected();
        if (!detected) {
            Logger.info("MecDriver -> Robot is straight, but no front line detected to center on!");
            return true;
        }

        boolean centered = Vision.isFrontCentered();
        if (!centered) return false;

        Logger.info("MecDriver -> Robot is straight, and centered, fully aligned!");
        return true;
    }

}
