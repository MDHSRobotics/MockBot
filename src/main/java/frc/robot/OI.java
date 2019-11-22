
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import frc.robot.commands.idle.*;
import frc.robot.commands.reactive.*;
import frc.robot.consoles.Logger;
import frc.robot.helpers.*;

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
    }

    //-------------------------//
    // Bind Controller Buttons //
    //-------------------------//
 
    // Drive XBox Buttons
    public static void configureDriveXBoxButtons() {
        // Bind the "drive" xbox buttons to specific commands
        //Devices.driveXboxBtnStart.whenPressed(new RobotGameModeDelivery());
        //Devices.driveXboxBtnBack.whenPressed(new RobotGameModeClimb());
        //Devices.driveXboxBtnDpad.whileHeld(new MecDriveAlign());
        // Devices.driveXboxBtnA.whenPressed(new LiftPulleyRaise());
        // Devices.driveXboxBtnB.whenPressed(new LiftPulleyLower());
        Devices.driveXboxBtnA.whenPressed(new TestPneumaticOpen());
        Devices.driveXboxBtnB.whenPressed(new TestPneumaticClose());
        Devices.driveXboxBtnY.whenPressed(new TestPneumaticStart());

        // Test drive commands
        // Devices.driveXboxBtnA.whileHeld(new MecDriveSlowForward());
        // Devices.driveXboxBtnB.whileHeld(new MecDriveSlowTurnRight());
        // Devices.driveXboxBtnX.whileHeld(new MecDriveSlowOrbitInwardClockwise());
        // Devices.driveXboxBtnY.whileHeld(new MecDriveSlowOrbitOutwardClockwise());
    }

    //----------------------//
    // Active Control Stick //
    //----------------------//

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

}
