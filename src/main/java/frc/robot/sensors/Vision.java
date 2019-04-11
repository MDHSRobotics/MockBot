
package frc.robot.sensors;

import frc.robot.Brain;


public class Vision {

    public static final int CAM_RESOLUTION_WIDTH = 320;
    public static final int CAM_RESOLUTION_HEIGHT = 240;

    private static final double MINIMUM_AREA = (CAM_RESOLUTION_HEIGHT / 3) ^ 2;

    // private static final double ANGLE_TARGET = 90;
    // private static final double ANGLE_THRESHOLD = 5;

    private static final double CENTER_X_TARGET = CAM_RESOLUTION_WIDTH / 2;
    private static final double CENTER_X_THRESHOLD = CAM_RESOLUTION_WIDTH / 64;

    public static boolean frontLineDetected() {
        double area = Brain.getFrontLineArea();
        boolean detected = lineDetected(area);
        return detected;
    }

    public static boolean leftLineDetected() {
        double area = Brain.getLeftLineArea();
        boolean detected = lineDetected(area);
        return detected;
    }

    public static boolean rightLineDetected() {
        double area = Brain.getRightLineArea();
        boolean detected = lineDetected(area);
        return detected;
    }

    public static boolean lineDetected(double area) {
        boolean detected = area >= MINIMUM_AREA;
        return detected;
    }

    public static boolean isFrontCentered() {
        double centerX = Brain.getFrontLineXcenter();
        boolean centered = isCentered(centerX);
        return centered;
    }

    public static boolean isLeftCentered() {
        double centerX = Brain.getLeftLineXcenter();
        boolean centered = isCentered(centerX);
        return centered;
    }

    public static boolean isRightCentered() {
        double centerX = Brain.getRightLineXcenter();
        boolean centered = isCentered(centerX);
        return centered;
    }

    public static boolean isCentered(double centerX) {
        boolean centered = (CENTER_X_TARGET - CENTER_X_THRESHOLD <= centerX && centerX <= CENTER_X_TARGET + CENTER_X_THRESHOLD);
        return centered;
    }

    public static double getFrontCorrectedX() {
        double centerX = Brain.getFrontLineXcenter();
        double x = getCorrectedX(centerX);
        return x;
    }

    public static double getLeftCorrectedX() {
        double centerX = Brain.getLeftLineXcenter();
        double x = getCorrectedX(centerX);
        return x;
    }

    public static double getRightCorrectedX() {
        double centerX = Brain.getRightLineXcenter();
        double x = getCorrectedX(centerX);
        return x;
    }

    public static double getCorrectedX(double centerX) {
        double x = CENTER_X_TARGET - centerX;
        return x;
    }

}
