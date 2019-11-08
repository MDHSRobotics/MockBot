
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Devices;
import frc.robot.commands.idle.ClawWheelStop;
import frc.robot.consoles.Logger;
import frc.robot.helpers.TalonConstants;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;


// Claw Wheel Subsytem, for sucking in boxes and spitting them out thru the barrier
public class Claw extends Subsystem {

    // Encoder constants
    private final boolean SENSOR_PHASE = true; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = false; // Which direction you want to be positive; this does not affect motor invert

    private final double OPEN_POSITION = 0.2;
    private final double WHEEL_POWER = 0;

    private boolean m_talonsAreConnected = false;
    
    public Claw() {
        Logger.setup("Constructing Subsystem: ClawWheel...");

        
        boolean talonAIsConnected = Devices.isConnected(Devices.talonSrxLeftClawWheel);
        boolean talonBIsConnected = Devices.isConnected(Devices.talonSrxRightClawWheel);
        m_talonsAreConnected = (talonAIsConnected &&
                                talonBIsConnected);
        
        if (!m_talonsAreConnected) {
            Logger.error("ClawWheel talons not all connected! Disabling ClawWheel...");
        }
        else {
            Devices.talonSrxLeftClawWheel.configFactoryDefault();
            Devices.talonSrxRightClawWheel.configFactoryDefault();
        }
    
        if (!m_talonsAreConnected) {
            Logger.error("ClawWheel talons not all connected! Disabling ClawWheel...");
        }
        else {
            Devices.talonSrxRightClawWheel.configFactoryDefault();
        }
    //To do: press right bumper to open claw, press Left bumper to close claw, press A to spit out box
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

    public void openClaw() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLeftClawWheel.set(WHEEL_POWER);
        Devices.talonSrxRightClawWheel.set(WHEEL_POWER);
    }
    public void closeClaw() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLeftClawWheel.set(-WHEEL_POWER);
        Devices.talonSrxRightClawWheel.set(-WHEEL_POWER);
    }

}
