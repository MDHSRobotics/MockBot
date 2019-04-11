
package frc.robot.sensors;

import frc.robot.consoles.Logger;
import frc.robot.Devices;


public class Gyro {

    public static final double YAW_TOLERANCE = 5;

    // Returns true if the gyro yaw matches the target angle within the YAW_TOLERANCE
    public static boolean isYawAligned(double targetAngle) {
        double angle = Devices.gyro.getYaw();
        double difference = Math.abs(targetAngle - angle);
        if (difference > 180) difference = 360 - difference;
        boolean aligned = (difference <= YAW_TOLERANCE);
        Logger.info("Gyro -> Target Angle: " + targetAngle + "; Gyro Yaw: " + angle + "; Difference: " + difference);
        return aligned;
    }

}
