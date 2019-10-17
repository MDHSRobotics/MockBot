package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.idle.LiftPulleyStop;
import frc.robot.consoles.Logger;
import frc.robot.Devices;


public class LiftPulley extends Subsystem {

    private boolean m_talonsAreConnected = false;

    public LiftPulley() {

        Logger.setup("Constructing Subsystem: LiftPulley...");

        boolean talonAIsConnected = Devices.isConnected(Devices.talonSrxLiftPulleyA);
        m_talonsAreConnected = (talonAIsConnected);
        if (!m_talonsAreConnected) {
            Logger.error("Pulley talons not all connected! Disabling LiftPulley...");
            }
            else {
                // TODO: Value of "1" needs to be replaced
                Devices.talonSrxLiftPulleyA.configOpenloopRamp(1);
                

        }
    }
    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing Pulley DefaultCommand -> LiftPulleyStop...");

        setDefaultCommand(new LiftPulleyStop());
    }
    // Stop motors
    public void stop() {
        if (!m_talonsAreConnected) return;
            Devices.talonSrxLiftPulleyA.stopMotor();
    }
    // Set motor speed
    public void setSpeed(double speed) {
        if (!m_talonsAreConnected) return;
            Devices.talonSrxLiftPulleyA.set(speed);
    }

}