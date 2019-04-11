
package frc.robot.sensors;

import frc.robot.Brain;


public class Distance {


    private static final double TARGET_DISTANCE = 3;

    public static boolean distanceReached() {
        double distance = Brain.getDistance();
        boolean isDistancedReached = (distance == TARGET_DISTANCE);
        return isDistancedReached;
    }

}