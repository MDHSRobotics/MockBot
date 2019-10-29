
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Devices;
import frc.robot.commands.idle.ClawWheelStop;
import frc.robot.consoles.Logger;


// Claw Wheel Subsytem, for sucking in boxes and spitting them out thru the barrier
public class ClawWheel extends Subsystem {

    // Encoder constants
    private final boolean SENSOR_PHASE = true; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = false; // Which direction you want to be positive; this does not affect motor invert

    private boolean m_talonsAreConnected = false;
    
    public ClawWheel() {
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

            Devices.talonSrxRightClawWheel.configPeakCurrentDuration(TalonConstants.PEAK_AMPERAGE_DURATION, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxRightClawWheel.configPeakCurrentLimit(TalonConstants.PEAK_AMPERAGE, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxRightClawWheel.configContinuousCurrentLimit(TalonConstants.CONTINUOUS_AMPERAGE_LIMIT, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxRightClawWheel.configNominalOutputForward(0);
            Devices.talonSrxRightClawWheel.configNominalOutputReverse(0);
            Devices.talonSrxRightClawWheel.configPeakOutputForward(0.5);
            Devices.talonSrxRightClawWheel.configPeakOutputReverse(-0.5);

            Devices.talonSrxRightClawWheel.configMotionAcceleration(3000, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxRightClawWheel.configMotionCruiseVelocity(8000, TalonConstants.TIMEOUT_MS);

            // Config TalonSRX Redline encoder
            Devices.talonSrxRightClawWheel.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxRightClawWheel.setSensorPhase(SENSOR_PHASE);
            Devices.talonSrxRightClawWheel.setInverted(MOTOR_INVERT);
            Devices.talonSrxRightClawWheel.configAllowableClosedloopError(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxRightClawWheel.config_kF(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxRightClawWheel.config_kP(TalonConstants.PID_LOOP_PRIMARY, 0.32, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxRightClawWheel.config_kI(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxRightClawWheel.config_kD(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);

            // Initialize current encoder position as zero 
            Devices.talonSrxRightClawWheel.setSelectedSensorPosition(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            SensorCollection sensorCol = Devices.talonSrxRightClawWheel.getSensorCollection();
            int absolutePosition = sensorCol.getPulseWidthPosition();
            absolutePosition &= 0xFFF;
            if (SENSOR_PHASE) absolutePosition *= -1;
            if (MOTOR_INVERT) absolutePosition *= -1;
            // Set the quadrature (relative) sensor to match absolute
            Devices.talonSrxRightClawWheel.setSelectedSensorPosition(absolutePosition, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
        }
    //To do: press right bumper to open claw, press Left bumper to close claw, press A to spit out box

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
