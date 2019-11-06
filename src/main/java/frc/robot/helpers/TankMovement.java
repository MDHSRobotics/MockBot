
package frc.robot.helpers;


// The values needed to drive using cartesian coordinates
public class TankMovement {

    public double ySpeed = 0; // Side to Side
    public double xSpeed = 0; // Forward & Backward
    public double zRotation = 0; // Rotate

    public TankMovement() {
    }

    public TankMovement(double y, double x, double z) {
        ySpeed = y;
        xSpeed = x;
        zRotation = z;
    }

}