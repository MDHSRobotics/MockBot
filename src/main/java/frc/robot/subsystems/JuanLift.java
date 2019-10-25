
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Devices;
import frc.robot.commands.interactive.MoveLift;
import frc.robot.consoles.Logger;
import frc.robot.helpers.TalonConstants;

// JuanLift subsystem, for raising and lowering boxes
public class JuanLift extends Subsystem {

    // Position constants
    private final double GEAR_RATIO = 16; //TODO change the gear ratio
    private final double RAISE_POSITION = 50; //TODO change the raise position
    private final double LOWER_POSITION = 0;

    // Encoder constants
    private final boolean SENSOR_PHASE = false; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = true; // Which direction you want to be positive; this does not affect motor invert

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public JuanLift() {
        Logger.setup("Constructing Subsystem: JuanLift...");
        

        boolean talonAIsConnected = Devices.isConnected(Devices.talonSrxLiftA);
        boolean talonBIsConnected = Devices.isConnected(Devices.talonSrxLiftB);
        m_talonsAreConnected = (talonAIsConnected &&
                                talonBIsConnected);

        if (!m_talonsAreConnected) {
            Logger.error("JuanLift talons not all connected! Disabling JuanLift...");
        }
        else {
            Devices.talonSrxLiftA.configFactoryDefault();

            Devices.talonSrxLiftA.configPeakCurrentDuration(TalonConstants.PEAK_AMPERAGE_DURATION, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftA.configPeakCurrentLimit(TalonConstants.PEAK_AMPERAGE, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftA.configContinuousCurrentLimit(TalonConstants.CONTINUOUS_AMPERAGE_LIMIT, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxLiftA.configNominalOutputForward(0);
            Devices.talonSrxLiftA.configNominalOutputReverse(0);
            Devices.talonSrxLiftA.configPeakOutputForward(0.4);
            Devices.talonSrxLiftA.configPeakOutputReverse(-0.3);

            Devices.talonSrxLiftA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftA.setSensorPhase(SENSOR_PHASE);
            Devices.talonSrxLiftA.setInverted(MOTOR_INVERT);
            Devices.talonSrxLiftA.configAllowableClosedloopError(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxLiftA.config_kF(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftA.config_kP(TalonConstants.PID_LOOP_PRIMARY, 0.2, TalonConstants.TIMEOUT_MS); //0.0125
            Devices.talonSrxLiftA.config_kI(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxLiftA.config_kD(TalonConstants.PID_LOOP_PRIMARY, 0.1, TalonConstants.TIMEOUT_MS);

            // Reset Encoder Position 
            Devices.talonSrxLiftA.setSelectedSensorPosition(0, TalonConstants.PID_SLOT_0, TalonConstants.TIMEOUT_MS);
            SensorCollection sensorCol = Devices.talonSrxLiftA.getSensorCollection();
            int absolutePosition = sensorCol.getPulseWidthPosition();
            absolutePosition &= 0xFFF;
            if (SENSOR_PHASE) absolutePosition *= -1;
            if (MOTOR_INVERT) absolutePosition *= -1;
            // Set the quadrature (relative) sensor to match absolute
            Devices.talonSrxLiftA.setSelectedSensorPosition(absolutePosition, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            
            Devices.talonSrxLiftA.configMotionAcceleration(5000, 20);
            Devices.talonSrxLiftA.configMotionCruiseVelocity(10000, 20);

            Devices.talonSrxLiftB.follow(Devices.talonSrxLiftA);
        }
    }

    @Override
    
    public void initDefaultCommand() {
        Logger.setup("Initializing JuanLift DefaultCommand");
        setDefaultCommand(new MoveLift()); 
    }

    // Stop the JuanLift motors
    public void stop() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLiftA.stopMotor();
        Devices.talonSrxLiftB.stopMotor();
    }

    public void resetPosition() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLiftA.set(0);
        Devices.talonSrxLiftA.setSelectedSensorPosition(0);
    }

    // Raise the JuanLift lift
    public void raise() {
        Logger.info("JuanLift -> RAISE " + RAISE_POSITION + " ticks");

        if (!m_talonsAreConnected) return;
        Devices.talonSrxLiftA.set(ControlMode.Position, RAISE_POSITION);
    }
    
    // Lower the JuanLift lift
    public void lower() {
        Logger.info("JuanLift -> LOWER " + LOWER_POSITION + " ticks");

        if (!m_talonsAreConnected) return;
        Devices.talonSrxLiftA.set(ControlMode.Position, LOWER_POSITION);
    }

    // Set JuanLift's move speed
    public void setMovePower(double movePower) {
        if (!m_talonsAreConnected) return;
        int currentPosition = getPosition();

        // Check whether the move is up or down 
        boolean upwardDirection = movePower > 0;
        if (upwardDirection) {
            // Going upward should not exceed upward limit
            if(currentPosition <= RAISE_POSITION) {
                Devices.talonSrxLiftA.set(movePower);
            }
        }
        else {
            // Going downward should not exceed lower limit
            if (currentPosition >= LOWER_POSITION) { 
                Devices.talonSrxLiftA.set(movePower);
        }
    }
    
    } // TODO set an encoder stop

    // Get the current JuanLift lift motor velocity
    public int getVelocity() {
        if (!m_talonsAreConnected) return 0;
        return Devices.talonSrxLiftA.getSelectedSensorVelocity();
    }

    // Get the current JuanLift lift motor position
    public int getPosition() {
        if (!m_talonsAreConnected) return 0;
        return Devices.talonSrxLiftA.getSelectedSensorPosition();
    }

    //---------//
    // Testing //
    //---------//

    public void testMotor() {
        if (!m_talonsAreConnected) return;
        Devices.talonSrxLiftA.set(0.2);
    }

}
