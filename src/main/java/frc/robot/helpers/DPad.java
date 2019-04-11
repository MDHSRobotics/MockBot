
package frc.robot.helpers;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.GenericHID;


public class DPad extends Button {

    public GenericHID device;

    public DPad(GenericHID humanInterfaceDevice) {
        this.device = humanInterfaceDevice;
    }

    @Override
    public boolean get() {
        int angle = device.getPOV(0);
        return (angle != -1);
    }

}