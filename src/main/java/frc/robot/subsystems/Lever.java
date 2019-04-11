
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.reactive.LeverDown;
import frc.robot.consoles.Logger;
import frc.robot.helpers.TalonConstants;
import frc.robot.Devices;


// Lever subsystem, for holding and tossing cargo levers
public class Lever extends Subsystem {

    // The public property to determine the Lever's flipper state
    public boolean leverIsUp = false;

    // Position constants
    private final double GEAR_RATIO = 16;
    private final double DOWN_POSITION = 0;
    private final double UP_POSITION = 0;

    // Encoder constants
    private final boolean SENSOR_PHASE = true; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = false; // Which direction you want to be positive; this does not affect motor invert
    
    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public Lever() {
        Logger.setup("Constructing Subsystem: Lever...");

        m_talonsAreConnected = Devices.isConnected(Devices.talonSrxLever);
        if (!m_talonsAreConnected) {
            Logger.error("Lever talons not all connected! Disabling Lever...");
        }
        else {
            Devices.talonSrxLever.configFactoryDefault();

            Devices.talonSrxLever.configPeakCurrentDuration(TalonConstants.PEAK_AMPERAGE_DURATION, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLever.configPeakCurrentLimit(TalonConstants.PEAK_AMPERAGE, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLever.configContinuousCurrentLimit(TalonConstants.CONTINUOUS_AMPERAGE_LIMIT, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxLever.configNominalOutputForward(0);
            Devices.talonSrxLever.configNominalOutputReverse(0);
            Devices.talonSrxLever.configPeakOutputForward(0.4);
            Devices.talonSrxLever.configPeakOutputReverse(-0.3);

            Devices.talonSrxLever.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLever.setSensorPhase(SENSOR_PHASE);
            Devices.talonSrxLever.setInverted(MOTOR_INVERT);
            Devices.talonSrxLever.configAllowableClosedloopError(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxLever.config_kF(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLever.config_kP(TalonConstants.PID_LOOP_PRIMARY, 0.2, TalonConstants.TIMEOUT_MS); //0.0125
            Devices.talonSrxLever.config_kI(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLever.config_kD(TalonConstants.PID_LOOP_PRIMARY, 0.1, TalonConstants.TIMEOUT_MS);

            // Reset Encoder Position 
            Devices.talonSrxLever.setSelectedSensorPosition(0, TalonConstants.PID_SLOT_0, TalonConstants.TIMEOUT_MS);
            SensorCollection sensorCol = Devices.talonSrxLever.getSensorCollection();
            int absolutePosition = sensorCol.getPulseWidthPosition();
            absolutePosition &= 0xFFF;
            if (SENSOR_PHASE) absolutePosition *= -1;
            if (MOTOR_INVERT) absolutePosition *= -1;
            // Set the quadrature (relative) sensor to match absolute
            Devices.talonSrxLever.setSelectedSensorPosition(absolutePosition, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            
            Devices.talonSrxLever.configMotionAcceleration(5000, 20);
            Devices.talonSrxLever.configMotionCruiseVelocity(10000, 20);
        }
    }

    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing Lever DefaultCommand -> LeverDown...");

        setDefaultCommand(new LeverDown());
    }

    // Toggle the leverIsTossed state
    public void toggleLeverFlipperPosition() {
        leverIsUp = !leverIsUp;
    }

    // Stop the Lever motor
    public void stop() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLever.stopMotor();
    }

    public void resetPosition() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLever.set(-0.2);
        Devices.talonSrxLever.setSelectedSensorPosition(0);
    }

    // Move the Lever flipper back to the hold position
    public void downLever() {
        // double angle = Brain.getLeverTossAngle();
        // double ticks = TalonConstants.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Lever -> Set Position to DOWN: " + DOWN_POSITION + " ticks");

        if (!m_talonsAreConnected) return;
        Devices.talonSrxLever.set(ControlMode.Position, DOWN_POSITION);
    }

    // Move the Lever flipper to toss the lever
    public void upLever() {
        double angle = UP_POSITION;
        double ticks = TalonConstants.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Lever -> Set Position to UP: " + angle + " angle, " + ticks + " ticks");

        if (!m_talonsAreConnected) return;
        Devices.talonSrxLever.set(ControlMode.Position, ticks);
    }

    // Get the current Lever flipper motor velocity
    public int getVelocity() {
        if (!m_talonsAreConnected) return 0;
        return Devices.talonSrxLever.getSelectedSensorVelocity();
    }

    // Get the current Lever flipper motor position
    public int getPosition() {
        if (!m_talonsAreConnected) return 0;
        return Devices.talonSrxLever.getSelectedSensorPosition();
    }

    //---------//
    // Testing //
    //---------//

    public void testMotor() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLever.set(0.2);
    }

}
