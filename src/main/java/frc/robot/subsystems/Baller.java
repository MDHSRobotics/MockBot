
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.reactive.BallHold;
import frc.robot.consoles.Logger;
import frc.robot.helpers.TalonConstants;
import frc.robot.Brain;
import frc.robot.Devices;


// Baller subsystem, for holding and tossing cargo balls
public class Baller extends Subsystem {

    // The public property to determine the Baller's flipper state
    public boolean ballIsTossed = false;

    // Position constants
    private final double GEAR_RATIO = 16;
    private final double HOLD_POSITION = 0;

    // Encoder constants
    private final boolean SENSOR_PHASE = false; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = true; // Which direction you want to be positive; this does not affect motor invert

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public Baller() {
        Logger.setup("Constructing Subsystem: Baller...");

        m_talonsAreConnected = Devices.isConnected(Devices.talonSrxBaller);
        if (!m_talonsAreConnected) {
            Logger.error("Baller talons not all connected! Disabling Baller...");
        }
        else {
            Devices.talonSrxBaller.configFactoryDefault();

            Devices.talonSrxBaller.configPeakCurrentDuration(TalonConstants.PEAK_AMPERAGE_DURATION, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxBaller.configPeakCurrentLimit(TalonConstants.PEAK_AMPERAGE, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxBaller.configContinuousCurrentLimit(TalonConstants.CONTINUOUS_AMPERAGE_LIMIT, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxBaller.configNominalOutputForward(0);
            Devices.talonSrxBaller.configNominalOutputReverse(0);
            Devices.talonSrxBaller.configPeakOutputForward(0.4);
            Devices.talonSrxBaller.configPeakOutputReverse(-0.3);

            Devices.talonSrxBaller.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxBaller.setSensorPhase(SENSOR_PHASE);
            Devices.talonSrxBaller.setInverted(MOTOR_INVERT);
            Devices.talonSrxBaller.configAllowableClosedloopError(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxBaller.config_kF(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxBaller.config_kP(TalonConstants.PID_LOOP_PRIMARY, 0.2, TalonConstants.TIMEOUT_MS); //0.0125
            Devices.talonSrxBaller.config_kI(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxBaller.config_kD(TalonConstants.PID_LOOP_PRIMARY, 0.1, TalonConstants.TIMEOUT_MS);

            // Reset Encoder Position 
            Devices.talonSrxBaller.setSelectedSensorPosition(0, TalonConstants.PID_SLOT_0, TalonConstants.TIMEOUT_MS);
            SensorCollection sensorCol = Devices.talonSrxBaller.getSensorCollection();
            int absolutePosition = sensorCol.getPulseWidthPosition();
            absolutePosition &= 0xFFF;
            if (SENSOR_PHASE) absolutePosition *= -1;
            if (MOTOR_INVERT) absolutePosition *= -1;
            // Set the quadrature (relative) sensor to match absolute
            Devices.talonSrxBaller.setSelectedSensorPosition(absolutePosition, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            
            Devices.talonSrxBaller.configMotionAcceleration(5000, 20);
            Devices.talonSrxBaller.configMotionCruiseVelocity(10000, 20);
        }
    }

    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing Baller DefaultCommand -> BallHold...");

        setDefaultCommand(new BallHold());
    }

    // Toggle the ballIsTossed state
    public void toggleBallFlipperPosition() {
        ballIsTossed = !ballIsTossed;
    }

    // Stop the Baller motor
    public void stop() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxBaller.stopMotor();
    }

    public void resetPosition() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxBaller.set(-0.2);
        Devices.talonSrxBaller.setSelectedSensorPosition(0);
    }

    // Move the Baller flipper back to the hold position
    public void holdBall() {
        Logger.info("Baller -> Set Position to HOLD: " + HOLD_POSITION + " ticks");

        if (!m_talonsAreConnected) return;
        Devices.talonSrxBaller.set(ControlMode.Position, HOLD_POSITION);
    }

    // Move the Baller flipper to toss the ball
    public void tossBall() {
        double angle = Brain.getBallTossAngle();
        double ticks = TalonConstants.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Baller -> Set Position to TOSS: " + angle + " angle, " + ticks + " ticks");

        if (!m_talonsAreConnected) return;
        Devices.talonSrxBaller.set(ControlMode.Position, ticks);
    }

    // Get the current Baller flipper motor velocity
    public int getVelocity() {
        if (!m_talonsAreConnected) return 0;
        return Devices.talonSrxBaller.getSelectedSensorVelocity();
    }

    // Get the current Baller flipper motor position
    public int getPosition() {
        if (!m_talonsAreConnected) return 0;
        return Devices.talonSrxBaller.getSelectedSensorPosition();
    }

    //---------//
    // Testing //
    //---------//

    public void testMotor() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxBaller.set(0.2);
    }

}
