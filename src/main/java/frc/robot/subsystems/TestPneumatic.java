
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.idle.TestPneumaticStop;
import frc.robot.consoles.Logger;
import frc.robot.Devices;


// TestPneumatic subsystem, for testing solenoid actuation
public class TestPneumatic extends Subsystem {

    public TestPneumatic() {
        Logger.setup("Constructing Subsystem: TestPneumatic...");

        Devices.pcm.setClosedLoopControl(true);
    }

    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing TestPneumatic DefaultCommand -> TestPneumaticStop...");

        setDefaultCommand(new TestPneumaticStop());
    }

    // Stop the compressor
    public void stop() {
        Devices.pcm.stop();
    }

    // Start the compressor
    public void start() {
        Devices.pcm.start();
    }

    // Extend the solenoid
    public void openSolenoid(){
        Devices.testSolenoid.set(true);
    }

    // Retract the solenoid
    public void closeSolenoid(){
        Devices.testSolenoid.set(false);
    }
}