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
        boolean talonBIsConnected = Devices.isConnected(Devices.talonSrxLiftPulleyB);
        boolean talonCIsConnected = Devices.isConnected(Devices.talonSrxLiftPulleyC);
        m_talonsAreConnected = (talonAIsConnected &&
                                talonBIsConnected && 
                                talonCIsConnected);
        if (!m_talonsAreConnected) {
            Logger.error("Pulley talons not all connected! Disabling LiftPulley...");
             }
            else {
                Devices.talonSrxLiftPulleyA.configOpenloopRamp(1);
                Devices.talonSrxLiftPulleyB.configOpenloopRamp(1);
                Devices.talonSrxLiftPulleyC.configOpenloopRamp(1);
            

    }




}
