
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;

import frc.robot.commands.reactive.LightToggle;
import frc.robot.consoles.Logger;
import frc.robot.Devices;


// Lighter Subsystem
public class Lighter extends Subsystem {
    
    public Lighter() {
        Logger.setup("Constructing Subsystem: Lighter...");
    }

    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing Lighter DefaultCommand -> LightToggle...");

        setDefaultCommand(new LightToggle());
    }

    public void turnOnBoth() {
        Devices.lighterRelay.set(Relay.Value.kOn);
    }

    public void turnOffBoth() {
        Devices.lighterRelay.set(Relay.Value.kOff);
    }

    public void turnOnWhiteOnly() {
        Devices.lighterRelay.set(Relay.Value.kForward);
    }

    public void turnOnRedOnly() {
        Devices.lighterRelay.set(Relay.Value.kReverse);
    }

}
