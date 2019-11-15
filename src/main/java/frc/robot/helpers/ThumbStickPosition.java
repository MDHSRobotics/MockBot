
package frc.robot.helpers;


// The position values obtained from Xbox Thumbsticks
public class ThumbStickPosition {

    public double yLeftPosition = 0;
    public double xLeftPosition = 0;
    public double xRightPosition = 0;
    public double yRightPosition = 0;

    public ThumbStickPosition() {
    }

    public ThumbStickPosition(double yLeft, double xLeft, double xRight, double yRight) {
        yLeftPosition = yLeft;
        xLeftPosition = xLeft;
        xRightPosition = xRight;
        yRightPosition = yRight;
    }

}
