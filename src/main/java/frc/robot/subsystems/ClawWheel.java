
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.idle.HatcherStop;
import frc.robot.consoles.Logger;
import frc.robot.helpers.TalonConstants;
import frc.robot.Brain;
import frc.robot.Devices;


// Hatcher subsystem, for grabbing and releasing hatches
public class ClawWheel extends Subsystem {

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public ClawWheel() {
        Logger.setup("Constructing Subsystem: ClawWheel...");

        boolean m_talonsAreConnected = Devices.isConnected(Devices.talonSrxLeftClawWheel);
        
        if (!m_talonsAreConnected) {
            Logger.error("ClawWheel talons not all connected! Disabling ClawWheel...");
        }
        else {
            Devices.talonSrxLeftClawWheel.configFactoryDefault();
            //Devices.talonSrxRightClawWheel.configFactoryDefault();
        }
    }

    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing Hatcher DefaultCommand -> HatchClawOpen...");

        setDefaultCommand(new HatcherStop());
    }

    // Stop the Hatcher claw motor
    public void stop() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLeftClawWheel.stopMotor();
        //Devices.talonSrxRightClawWheel.stopMotor();
        Logger.setup("Wheel Motors Disconnected! Shutting down wheels...");
    }

    public void testMotor() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLeftClawWheel.set(0.2);
        //Devices.talonSrxRightClawWheel.set(0.2);
    }

}