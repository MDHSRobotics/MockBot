
package frc.robot.helpers;


// The values needed to drive using Tank
public class TankMovement {

    public double yLeftPosition = 0;
    public double yRightPosition = 0;

    public TankMovement() {
    }

    public TankMovement(double yLeft, double yRight) {
        yLeftPosition = yLeft;
        yRightPosition = yRight;
    }

}