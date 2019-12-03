
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.idle.TestPneumaticStop;
import frc.robot.consoles.Logger;
import frc.robot.Devices;


// TestPneumatic subsystem, for testing solenoid actuation
public class TestPneumatic extends Subsystem {

    private boolean m_pcmIsNotConnected = false;

    public TestPneumatic() {

        m_pcmIsNotConnected = Devices.pcm.getCompressorNotConnectedFault();

        if(m_pcmIsNotConnected){
            Logger.error("TestPneumatic compressor is not connected! Disabling TestPneumatic...");

            Devices.pcm.setClosedLoopControl(false);
        }
        else{
            Logger.setup("Constructing Subsystem: TestPneumatic...");

            Devices.pcm.setClosedLoopControl(true);
        }
    }

    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing TestPneumatic DefaultCommand -> TestPneumaticStop...");

        setDefaultCommand(new TestPneumaticStop());
    }

    // Stop the compressor
    public void stop() {
        if(m_pcmIsNotConnected) return;
        Devices.pcm.stop();
    }

    // Start the compressor
    public void start() {
        if(m_pcmIsNotConnected) return;
        Devices.pcm.start();
    }

    // Extend the solenoid
    public void openSolenoid(){
        if(m_pcmIsNotConnected) return;
        Devices.testSolenoid.set(true);
    }

    // Retract the solenoid
    public void closeSolenoid(){
        if(m_pcmIsNotConnected) return;
        Devices.testSolenoid.set(false);
    }

    public boolean isUnderPressure(){
        return Devices.pcm.getPressureSwitchValue();
    }
}