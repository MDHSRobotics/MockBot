
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.idle.TestPneumaticStop;
import frc.robot.consoles.Logger;
import frc.robot.helpers.TalonUtils;
import frc.robot.Devices;


// Hatcher subsystem, for grabbing and releasing hatches
public class TestPneumatic extends Subsystem {

    // The pcm connection state, to prevent watchdog warnings during testing
    private boolean m_pcmIsConnected = false;

    public TestPneumatic() {
        Logger.setup("Constructing Subsystem: TestPneumatic...");

        Devices.pcm.setClosedLoopControl(true);
    }

    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing TestPneumatic DefaultCommand -> TestPneumaticStop...");

        setDefaultCommand(new TestPneumaticStop());
    }

    // // Toggle the clawIsClosed state
    // public void toggleClawPosition() {
    //     clawIsClosed = !clawIsClosed;
    // }

    // Stop the Hatcher claw motor
    public void stop() {
        Devices.pcm.stop();
    }

    public void openSolenoid(){
        Devices.pcm.start();
        Devices.testSolenoid.set(true);
    }

    public void closeSolenoid(){
        Devices.pcm.start();
        Devices.testSolenoid.set(false);
    }
}