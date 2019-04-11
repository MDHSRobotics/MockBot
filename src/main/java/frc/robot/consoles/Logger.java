
package frc.robot.consoles;

import edu.wpi.first.wpilibj.DriverStation;
import java.time.LocalTime;


// This is a helper class for colorful console logging.
public class Logger {

    public Logger() {
        // C:\Users\Public\frc2019\vscode\data\extensions\wpilibsuite.vscode-wpilib-2019.2.1\resources\dist\riologpage.js
 }

    // Grey text for constructor logging, etc.
    public static void setup(Object message) {
        System.out.println("setup --> " + LocalTime.now() + " :: " + message);
    }

    // Pink text for waiting
    public static void waiting(Object message) {
        System.out.println("waiting --> " + LocalTime.now() + " :: " + message);
    }

    // Green text for taking an action, like starting a command
    public static void action(Object message) {
        System.out.println("ACTION --> " + LocalTime.now() + " :: " + message);
    }

    // Orange text for info, values, etc.
    public static void info(Object message) {
        System.out.println("INFO --> " + LocalTime.now() + " :: " + message);
    }

    // Blue text for ending or interrupting, etc.    
    public static void ending(Object message) {
        System.out.println("ending --> " + LocalTime.now() + " :: " + message);
    }

    // Yellow text for warnings
    public static void warning(Object message) {
        DriverStation.reportWarning("WARNING --> " + message.toString(), false);
    }

    // Red text for warnings
    public static void error(Object message) {
        DriverStation.reportError("ERROR --> " + message.toString(), false);
    }

}
