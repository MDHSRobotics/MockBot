
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Devices;
import frc.robot.consoles.Logger;
import frc.robot.commands.idle.TestLifterStop;


public class TestLifter extends Subsystem {

    public boolean goingUp;

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    // public TestLifter() {
    //     TestLifter(true);
    // }

    public TestLifter(boolean startsGoingUp) {
        Logger.setup("Constructing Subsystem: TestLifter...");

        m_talonsAreConnected = Devices.isConnected(Devices.talonSrxTestLifter);
        if (!m_talonsAreConnected) {
            Logger.error("TestLifter talons not all connected! Disabling TestLifter...");
        }
        else { 
            Devices.talonSrxTestLifter.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
            Devices.talonSrxTestLifter.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        }
        goingUp = startsGoingUp;
    }
    
    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing TestLifter DefaultCommand -> TestLiftHold...");

        setDefaultCommand(new TestLifterStop());
    }

    public void stop() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxTestLifter.stopMotor();
    }

    public void liftUp() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxTestLifter.set(0.2);
    }

    public void liftDown() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxTestLifter.set(-0.2);
    }

    public boolean isUpLimitSwitchPushed() {
        boolean isPushed = Devices.talonSrxTestLifter.getSensorCollection().isFwdLimitSwitchClosed();
        return isPushed;
    }

    public boolean isDownLimitSwitchPushed() {
        boolean isPushed = Devices.talonSrxTestLifter.getSensorCollection().isRevLimitSwitchClosed();
        return isPushed;
    }
}