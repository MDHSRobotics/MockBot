
package frc.robot;

import frc.robot.consoles.Logger;

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

    }

}
