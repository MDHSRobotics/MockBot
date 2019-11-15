
package frc.robot.helpers;


// The values needed to drive using Arcade Drive
public class ArcadeMovement {

    public double speed = 0; // forward speed
    public double rotation = 0; // z rotation
    public boolean squareInputs = false; // if true, decreases input sensitivity at low speeds
    public double strafe = 0;

    public ArcadeMovement() {
    }

    public ArcadeMovement(double x, double z, boolean s, double st) {
        speed = x;
        rotation = z;
        squareInputs = s;
        strafe = st;
        
    }

}
