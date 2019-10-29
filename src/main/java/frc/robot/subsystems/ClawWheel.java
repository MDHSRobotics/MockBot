
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Devices;
import frc.robot.commands.idle.ClawWheelStop;
import frc.robot.consoles.Logger;


// Claw Wheel Subsytem, for sucking in boxes and spitting them out thru the barrier
public class ClawWheel extends Subsystem {

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public ClawWheel() {
        Logger.setup("Constructing Subsystem: ClawWheel...");

        boolean talonSrxLeftClawWheelisConnected = Devices.isConnected(Devices.talonSrxLeftClawWheel);
        boolean talonSrxRightClawWheelisConnected = Devices.isConnected(Devices.talonSrxRightClawWheel);
        m_talonsAreConnected = (talonSrxLeftClawWheelisConnected && talonSrxRightClawWheelisConnected);
    }

    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing ClawWheel DefaultCommand -> ClawWheelOpen...");

        setDefaultCommand(new ClawWheelStop());
    }


    // Stop the Hatcher claw motor
    public void stop() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLeftClawWheel.stopMotor();
        Devices.talonSrxRightClawWheel.stopMotor();
        Logger.setup("Wheel Motors Disconnected! Shutting down wheels...");
    }

    public void testMotor() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLeftClawWheel.set(0.2);
        Devices.talonSrxRightClawWheel.set(0.2);
    }
    public void closeMotor() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLeftClawWheel.set(-0.2);
        Devices.talonSrxRightClawWheel.set(-0.2);
    }

}
