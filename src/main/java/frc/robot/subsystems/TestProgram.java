
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
public class TestProgram extends Subsystem {

    // The public property to determine the Hatcher's claw state
    public boolean clawIsClosed = false;

    // Position constants
    private final double GEAR_RATIO = 16;

    // Encoder constants
    private final boolean SENSOR_PHASE = true; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = false; // Which direction you want to be positive; this does not affect motor invert

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public TestProgram() {
        Logger.setup("Constructing Subsystem: Hatcher...");

        m_talonsAreConnected = Devices.isConnected(Devices.talonTestBoard);
        if (!m_talonsAreConnected) {
            Logger.error("TestProgram talons not all connected! Disabling Hatcher...");
        }
        else {
            Devices.talonTestBoard.configFactoryDefault();

            Devices.talonTestBoard.configPeakCurrentDuration(TalonConstants.PEAK_AMPERAGE_DURATION, TalonConstants.TIMEOUT_MS);
            Devices.talonTestBoard.configPeakCurrentLimit(TalonConstants.PEAK_AMPERAGE, TalonConstants.TIMEOUT_MS);
            Devices.talonTestBoard.configContinuousCurrentLimit(TalonConstants.CONTINUOUS_AMPERAGE_LIMIT, TalonConstants.TIMEOUT_MS);

            Devices.talonTestBoard.configNominalOutputForward(0);
            Devices.talonTestBoard.configNominalOutputReverse(0);
            Devices.talonTestBoard.configPeakOutputForward(0.5);
            Devices.talonTestBoard.configPeakOutputReverse(-0.5);

            Devices.talonTestBoard.configMotionAcceleration(3000, TalonConstants.TIMEOUT_MS);
            Devices.talonTestBoard.configMotionCruiseVelocity(8000, TalonConstants.TIMEOUT_MS);

            // Config TalonSRX Redline encoder
            Devices.talonTestBoard.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            Devices.talonTestBoard.setSensorPhase(SENSOR_PHASE);
            Devices.talonTestBoard.setInverted(MOTOR_INVERT);
            Devices.talonTestBoard.configAllowableClosedloopError(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);

            Devices.talonTestBoard.config_kF(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonTestBoard.config_kP(TalonConstants.PID_LOOP_PRIMARY, 0.32, TalonConstants.TIMEOUT_MS);
            Devices.talonTestBoard.config_kI(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonTestBoard.config_kD(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);

            // Initialize current encoder position as zero 
            Devices.talonTestBoard.setSelectedSensorPosition(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            SensorCollection sensorCol = Devices.talonTestBoard.getSensorCollection();
            int absolutePosition = sensorCol.getPulseWidthPosition();
            absolutePosition &= 0xFFF;
            if (SENSOR_PHASE) absolutePosition *= -1;
            if (MOTOR_INVERT) absolutePosition *= -1;
            // Set the quadrature (relative) sensor to match absolute
            Devices.talonTestBoard.setSelectedSensorPosition(absolutePosition, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
        }
    }

    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing Hatcher DefaultCommand -> HatchClawOpen...");

        setDefaultCommand(new HatcherStop());
    }

    // Toggle the clawIsClosed state
    public void toggleClawPosition() {
        clawIsClosed = !clawIsClosed;
    }

    // Stop the Hatcher claw motor
    public void stop() {
        if (!m_talonsAreConnected) return;
        Devices.talonTestBoard.stopMotor();
    }

    // Open the Hatcher claw
    public void openClaw() {
        double angle = Brain.getHatchOpenAngle();
        double ticks = TalonConstants.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Hatcher -> Motion Magic to OPEN: " + angle + " angle, " + ticks + " ticks");

        if (!m_talonsAreConnected) return;
        Devices.talonTestBoard.set(ControlMode.MotionMagic, ticks);
    }

    // Close the Hatcher claw
    public void closeClaw() {
        double angle = Brain.getHatchCloseAngle();
        double ticks = TalonConstants.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Hatcher -> Motion Magic to CLOSE: " + angle + " angle, " + ticks + " ticks");

        if (!m_talonsAreConnected) return;
        Devices.talonTestBoard.set(ControlMode.MotionMagic, ticks);
    }

    // Get the current Hatcher claw motor velocity
    public int getVelocity() {
        if (!m_talonsAreConnected) return 0;
        return Devices.talonTestBoard.getSelectedSensorVelocity();
    }

    // Get the current Hatcher claw motor position
    public int getPosition() {
        if (!m_talonsAreConnected) return 0;
        return Devices.talonTestBoard.getSelectedSensorPosition();
    }

    //---------//
    // Testing //
    //---------//

    public void testMotor() {
        if (!m_talonsAreConnected) return;
        Devices.talonTestBoard.set(0.5);
    }

}