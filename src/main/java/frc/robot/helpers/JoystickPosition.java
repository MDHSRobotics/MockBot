
package frc.robot.helpers;


// The position values obtained from a Joystick
public class JoystickPosition {

    public double yPosition = 0; // Forward & Backward
    public double xPosition = 0; // Side to Side
    public double zPosition = 0; // Rotate

    public JoystickPosition() {
    }

    public JoystickPosition(double y, double x, double z) {
        yPosition = y;
        xPosition = x;
        zPosition = z;
    }

}
