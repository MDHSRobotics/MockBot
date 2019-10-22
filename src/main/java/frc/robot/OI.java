
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import frc.robot.commands.instant.*;
import frc.robot.commands.interactive.*;
import frc.robot.consoles.Logger;
import frc.robot.helpers.*;
import frc.robot.Brain;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public enum ControlStick {
        JOYSTICK, XBOX
    }

    // Constructor
    public OI() {
        Logger.setup("Constructing OI...");

        if (!Devices.isDriveXboxConnected()) {
            Logger.error("Drive XBox controller not plugged in!");
        }
        else {
            configureDriveXBoxButtons();
        }

        if (!Devices.isClimbXboxConnected()) {
            Logger.error("Climb XBox controller not plugged in!");
        }
        else {
            configureClimbXBoxButtons();
        }
    }

    //-------------------------//
    // Bind Controller Buttons //
    //-------------------------//
 
    // Drive XBox Buttons
    public static void configureDriveXBoxButtons() {
        // Bind the "drive" xbox buttons to specific commands
        Devices.driveXboxBtnStart.whenPressed(new RobotGameModeDelivery());
        Devices.driveXboxBtnBumperLeft.whenPressed(new RobotGameModeClimb());
        Devices.driveXboxBtnDpad.whileHeld(new MecDriveAlign());
        Devices.driveXboxBtnBumperLeft.whileHeld(new BallReset());
        Devices.driveXboxBtnBumperRight.whenPressed(new BallerToggleFlipperPosition());
        Devices.driveXboxBtnA.whenPressed(new LiftPulleyManual());
        //TODO: add second command for second lift position and assign it to button b
        // Test drive commands
        // Devices.driveXboxBtnA.whileHeld(new MecDriveSlowForward());
        // Devices.driveXboxBtnB.whileHeld(new MecDriveSlowTurnRight());
        // Devices.driveXboxBtnX.whileHeld(new MecDriveSlowOrbitInwardClockwise());
        // Devices.driveXboxBtnY.whileHeld(new MecDriveSlowOrbitOutwardClockwise());
    }

    // Climb XBox Buttons
    public static void configureClimbXBoxButtons() {
        // Bind the "climb" xbox buttons to specific commands
        Devices.climbXboxBtnBumperLeft.whenPressed(new RobotGameModeClimb());
        Devices.climbXboxBtnX.whenPressed(new LiftPulleyManual());
    }

    //----------------------//
    // Active Control Stick //
    //----------------------//

    // Determines the cartesian movement (forward/backward speed, side to side speed, rotation speed)
    // from the active control stick position(s)
    public static CartesianMovement getCartesianMovement(boolean isYflipped) {
        ControlStick cStick = Brain.getControlStick();
        switch (cStick) {
            case JOYSTICK:
                return getCartesianMovementFromJoystick(isYflipped);
            case XBOX:
                return getCartesianMovementFromThumbsticks(isYflipped);
            default:
                return null;
        }
    }

    // Determines the polar movement (magnitude, angle, rotation)
    // from the active control stick position(s)
    public static PolarMovement getPolarMovement(boolean isYflipped) {
        ControlStick cStick = Brain.getControlStick();
        switch (cStick) {
            case JOYSTICK:
                return getPolarMovementFromJoystick(isYflipped);
            case XBOX:
                return getPolarMovementFromThumbsticks(isYflipped);
            default:
                return null;
        }
    }

    //----------//
    // Joystick //
    //----------//

    // Determines the cartesian movement (forward/backward speed, side to side speed, rotation speed)
    // from the current joystick position
    public static CartesianMovement getCartesianMovementFromJoystick(boolean isYflipped) {
        JoystickPosition pos = getJoystickPosition(isYflipped);
        CartesianMovement move = new CartesianMovement(pos.xPosition, pos.yPosition, pos.zPosition);
        // Logger.info("Joystick Cartesian Movement: " + pos.yPosition + ", " + pos.xPosition + ", " + pos.zPosition);
        return move;
    }

    // Determines the polar movement (magnitude, angle, rotation)
    // from the current joystick position
    public static PolarMovement getPolarMovementFromJoystick(boolean isYflipped) {
        JoystickPosition pos = getJoystickPosition(isYflipped);
        PolarMovement move = new PolarMovement(pos.xPosition, pos.yPosition, pos.zPosition);
        return move;
    }

    // Gets the joystick position and applies user-determined orientation, deadzones, and sensitivity
    private static JoystickPosition getJoystickPosition(boolean isYflipped) {
        double y = Devices.jstick.getY(); // Forward & backward
        double x = Devices.jstick.getX(); // Side to side
        double z = Devices.jstick.getZ(); // Rotate

        // Forward/backward and rotation directions are both reversed from what is intuitive, so flip them
        y = -y;
        z = -z; // TODO: Low priority, but check to see if this should be deleted, like we did for thumbsticks

        // User-determined flipping of forward/backward orientation
        if (isYflipped) {
            y = -y;
        }

        // Deadzones
        double yDeadZone = Brain.getYdeadZone();
        double xDeadZone = Brain.getXdeadZone();
        double zDeadZone = Brain.getZdeadZone();

        if (Math.abs(y) <= yDeadZone) y = 0;
        if (Math.abs(x) <= xDeadZone) x = 0;
        if (Math.abs(z) <= zDeadZone) z = 0;

        if (y > 0) y = y - yDeadZone;
        if (y < 0) y = y + yDeadZone;
        if (x > 0) x = x - xDeadZone;
        if (x < 0) x = x + xDeadZone;
        if (z > 0) z = z - zDeadZone;
        if (z < 0) z = z + zDeadZone;

        // Sensitivity
        double ySensitivity = Brain.getYsensitivity();
        double xSensitivity = Brain.getXsensitivity();
        double zSensitivity = Brain.getXsensitivity();

        y = y * ySensitivity;
        x = x * xSensitivity;
        z = z * zSensitivity;

        JoystickPosition pos = new JoystickPosition(y, x, z);
        return pos;
    }

    //------------------//
    // Xbox Thumbsticks //
    //------------------//

    // Determines the speed at which to drive front wheels from the current xbox right hand trigger axis
    public static double getFrontWheelDriveSpeed() {
        double triggerAxis = Devices.driveXbox.getTriggerAxis(Hand.kLeft);
        return triggerAxis;
    }

    // Determines the cartesian movement (forward/backward speed, side to side speed, rotation speed)
    // from the current xbox thumbstick positions
    public static CartesianMovement getCartesianMovementFromThumbsticks(boolean isYleftFlipped) {
        ThumbStickPosition pos = getThumbstickPosition(isYleftFlipped);
        CartesianMovement move = new CartesianMovement(pos.xLeftPosition, pos.yLeftPosition, pos.xRightPosition);
        // Logger.info("Xbox Cartesian Movement: " + pos.yLeftPosition + ", " + pos.xLeftPosition + ", " + pos.xRightPosition);
        return move;
    }

    // Determines the polar movement (magnitude, angle, rotation)
    // from the current xbox thumbstick positions
    public static PolarMovement getPolarMovementFromThumbsticks(boolean isYleftFlipped) {
        ThumbStickPosition pos = getThumbstickPosition(isYleftFlipped);
        PolarMovement move = new PolarMovement(pos.yLeftPosition, pos.xLeftPosition, pos.xRightPosition);
        return move;
    }

    // Gets the xbox thumbstick positions and applies user-determined orientation, deadzones, and sensitivity
    private static ThumbStickPosition getThumbstickPosition(boolean isYleftFlipped) {
        double yLeft = Devices.driveXbox.getY(Hand.kLeft); // Forward & backward, flipped
        double xLeft = Devices.driveXbox.getX(Hand.kLeft); // Strafe
        double xRight = Devices.driveXbox.getX(Hand.kRight); // Rotate

        // Forward/backward direction is reversed from what is intuitive, so flip it
        yLeft = -yLeft;

        // User-determined flipping of forward/backward orientation
        if (isYleftFlipped) {
            yLeft = -yLeft;
        }

        // Deadzones
        double yLeftDeadZone = Brain.getYleftDeadZone();
        double xLeftDeadZone = Brain.getXleftDeadZone();
        double xRightDeadZone = Brain.getXrightDeadZone();

        if (Math.abs(yLeft) <= yLeftDeadZone) yLeft = 0;
        if (Math.abs(xLeft) <= xLeftDeadZone) xLeft = 0;
        if (Math.abs(xRight) <= xRightDeadZone) xRight = 0;

        if (yLeft > 0) yLeft = yLeft - yLeftDeadZone;
        if (yLeft < 0) yLeft = yLeft + yLeftDeadZone;
        if (xLeft > 0) xLeft = xLeft - xLeftDeadZone;
        if (xLeft < 0) xLeft = xLeft + xLeftDeadZone;
        if (xRight > 0) xRight = xRight - xRightDeadZone;
        if (xRight < 0) xRight = xRight + xRightDeadZone;

        // Sensitivity
        double yLeftSensitivity = Brain.getYleftSensitivity();
        double xLeftSensitivity = Brain.getXleftSensitivity();
        double xRightSensitivity = Brain.getXrightSensitivity();

        yLeft = yLeft * yLeftSensitivity;
        xLeft = xLeft * xLeftSensitivity;
        xRight = xRight * xRightSensitivity;

        ThumbStickPosition pos = new ThumbStickPosition(yLeft, xLeft, xRight);
        return pos;
    }

    // Converts the Dpad Angle (0 to 360, clockwise) into a Gyro Angle (0 to 180, clockwise, 0 to -180 counter-clockwise)
    public static int getDpadAngleForGyro() {
        int angle = Devices.driveXbox.getPOV(0);
        if (angle > 180) angle = angle - 360;
        return angle;
    }

    // TODO: Also consider adding a "debouncer" for the buttons
    // https://frc-pdr.readthedocs.io/en/latest/user_input/joystick.html
    
    //----------------------//
    // Interactive Climbing //
    //----------------------//

    // Gets the Pulley speed from the climb xbox controller's Left Thumbstick Y axis position
    public static double getLiftPulleyLiftSpeed() {
        double y = Devices.climbXbox.getY(Hand.kLeft);
        return y;
    }

    // Gets the Pulley speed from the drive Xbox controller's Right Thumbstick Y
    public static double getBallSpeed() {
        double triggerAxis = Devices.driveXbox.getTriggerAxis(Hand.kRight);
        return triggerAxis;
    }

}
