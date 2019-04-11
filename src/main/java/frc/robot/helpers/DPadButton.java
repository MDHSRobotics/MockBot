
package frc.robot.helpers;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.GenericHID;


public class DPadButton extends Button {

    public enum Direction {
        UP (0),
        UP_RIGHT (45),
        RIGHT (90),
        DOWN_RIGHT (135),
        DOWN (180),
        DOWN_LEFT (225),
        LEFT (270),
        UP_LEFT (315);

        public int degrees;
        Direction(int povAngle) {
            this.degrees = povAngle;
        }
    }

    public GenericHID device;
    public Direction direction;

    public DPadButton(GenericHID humanInterfaceDevice, Direction dPadButtonDirection) {
        this.device = humanInterfaceDevice;
        this.direction = dPadButtonDirection;
    }

    @Override
    public boolean get() {
        int angle = device.getPOV(0);
        return (angle == direction.degrees);
    }

}