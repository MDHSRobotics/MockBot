package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.idle.LiftPulleyStop;
import frc.robot.consoles.Logger;
import frc.robot.helpers.TalonConstants;
import frc.robot.Brain;
import frc.robot.Devices;


public class LiftPulley extends Subsystem {

    private final double SPROCKET_DIAMETER = 10;

    // Position constants
    private final double GEAR_RATIO = 16;

    // Encoder constants
    private final boolean SENSOR_PHASE = true; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = false; // Which direction you want to be positive; this does not affect motor invert

    private boolean m_talonsAreConnected = false;

    public LiftPulley() {

        Logger.setup("Constructing Subsystem: LiftPulley...");

        boolean talonAIsConnected = Devices.isConnected(Devices.talonSrxLiftPulleyA);
        m_talonsAreConnected = (talonAIsConnected);
        if (!m_talonsAreConnected) {
            Logger.error("Pulley talons not all connected! Disabling LiftPulley...");
            }
            else {
                Devices.talonSrxLiftPulleyA.configFactoryDefault();

            Devices.talonSrxLiftPulley.configPeakCurrentDuration(TalonConstants.PEAK_AMPERAGE_DURATION, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftPulley.configPeakCurrentLimit(TalonConstants.PEAK_AMPERAGE, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftPulley.configContinuousCurrentLimit(TalonConstants.CONTINUOUS_AMPERAGE_LIMIT, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxLiftPulley.configNominalOutputForward(0);
            Devices.talonSrxLiftPulley.configNominalOutputReverse(0);
            Devices.talonSrxLiftPulley.configPeakOutputForward(0.5);
            Devices.talonSrxLiftPulley.configPeakOutputReverse(-0.5);

            Devices.talonSrxLiftPulley.configMotionAcceleration(3000, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftPulley.configMotionCruiseVelocity(8000, TalonConstants.TIMEOUT_MS);

            // Config TalonSRX Redline encoder
            Devices.talonSrxLiftPulley.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftPulley.setSensorPhase(SENSOR_PHASE);
            Devices.talonSrxLiftPulley.setInverted(MOTOR_INVERT);
            Devices.talonSrxLiftPulley.configAllowableClosedloopError(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxLiftPulley.config_kF(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftPulley.config_kP(TalonConstants.PID_LOOP_PRIMARY, 0.32, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftPulley.config_kI(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftPulley.config_kD(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);

            // Initialize current encoder position as zero 
            Devices.talonSrxLiftPulley.setSelectedSensorPosition(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            SensorCollection sensorCol = Devices.talonSrxLiftPulley.getSensorCollection();
            int absolutePosition = sensorCol.getPulseWidthPosition();
            absolutePosition &= 0xFFF;
            if (SENSOR_PHASE) absolutePosition *= -1;
            if (MOTOR_INVERT) absolutePosition *= -1;
            // Set the quadrature (relative) sensor to match absolute
            Devices.talonSrxLiftPulley.setSelectedSensorPosition(absolutePosition, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
        }
    }
    
    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing Pulley DefaultCommand -> LiftPulleyStop...");

        setDefaultCommand(new LiftPulleyStop());
    }
    
    // Set motor speed
    public void setSpeed() {
        double distance = Brain.getLiftPulleyDistance();
        double ticks = TalonConstants.translateDistanceToTicks(distance, SPROCKET_DIAMETER, GEAR_RATIO);
        Logger.info("Hatcher -> Motion Magic to OPEN: " + angle + " angle, " + ticks + " ticks");

        if (!m_talonsAreConnected) return;
        Devices.talonSrxLiftPulley.set(ControlMode.MotionMagic, ticks);
    }

    // Stop motors
    public void stop() {
        if (!m_talonsAreConnected) return;
            Devices.talonSrxLiftPulley.stopMotor();
    }

}