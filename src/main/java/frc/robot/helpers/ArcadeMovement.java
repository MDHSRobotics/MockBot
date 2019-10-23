
package frc.robot.helpers;


// The values needed to drive using Arcade Drive
public class ArcadeMovement {

    public double speed = 0; // forward speed
    public double rotation = 0; // z rotation
    public boolean squareInputs = false; // if true, decreases input sensitivity at low speeds

    public ArcadeMovement() {
    }

    public ArcadeMovement(double x, double z, boolean s) {
        speed = x;
        rotation = z;
        squareInputs = s;
        
    }

}
