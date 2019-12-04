
package frc.robot;

import frc.robot.commands.idle.TestPneumaticStop;
import frc.robot.commands.reactive.*;
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
        Devices.driveXboxBtnA.whenPressed(new TestPneumaticOpen2());
        Devices.driveXboxBtnX.whenPressed(new TestPneumaticStop());
        Devices.driveXboxBtnB.whenPressed(new TestPneumaticClose2());
        Devices.driveXboxBtnY.whenPressed(new TestPneumaticStart());

        Devices.driveXboxBtnBumperLeft.whenPressed(new TestPneumaticOpen());
        Devices.driveXboxBtnBumperRight.whenPressed(new TestPneumaticClose());

    }

}
