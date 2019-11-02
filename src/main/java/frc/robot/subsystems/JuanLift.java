
package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Devices;
import frc.robot.commands.idle.LiftStop;
import frc.robot.consoles.Logger;
import frc.robot.helpers.TalonUtils;

// JuanLift subsystem, for raising and lowering boxes
public class JuanLift extends Subsystem {

    // Position constants
//  private final double GEAR_RATIO = 16; //TODO change the gear ratio
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

            Devices.talonSrxLiftA.configPeakCurrentDuration(TalonUtils.PEAK_AMPERAGE_DURATION, TalonUtils.TIMEOUT_MS);
            Devices.talonSrxLiftA.configPeakCurrentLimit(TalonUtils.PEAK_AMPERAGE, TalonUtils.TIMEOUT_MS);
            Devices.talonSrxLiftA.configContinuousCurrentLimit(TalonUtils.CONTINUOUS_AMPERAGE_LIMIT, TalonUtils.TIMEOUT_MS);

            Devices.talonSrxLiftA.configNominalOutputForward(0);
            Devices.talonSrxLiftA.configNominalOutputReverse(0);
            Devices.talonSrxLiftA.configPeakOutputForward(0.4);
            Devices.talonSrxLiftA.configPeakOutputReverse(-0.3);

            Devices.talonSrxLiftA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TalonUtils.PID_LOOP_PRIMARY, TalonUtils.TIMEOUT_MS);
            Devices.talonSrxLiftA.setSensorPhase(SENSOR_PHASE);
            Devices.talonSrxLiftA.setInverted(MOTOR_INVERT);
            Devices.talonSrxLiftA.configAllowableClosedloopError(TalonUtils.PID_LOOP_PRIMARY, 0, TalonUtils.TIMEOUT_MS);

            Devices.talonSrxLiftA.config_kF(TalonUtils.PID_LOOP_PRIMARY, 0.0, TalonUtils.TIMEOUT_MS);
            Devices.talonSrxLiftA.config_kP(TalonUtils.PID_LOOP_PRIMARY, 0.2, TalonUtils.TIMEOUT_MS); 
            Devices.talonSrxLiftA.config_kI(TalonUtils.PID_LOOP_PRIMARY, 0.0, TalonUtils.TIMEOUT_MS);
            Devices.talonSrxLiftA.config_kD(TalonUtils.PID_LOOP_PRIMARY, 0.1, TalonUtils.TIMEOUT_MS);

            // Reset Encoder Position 
            Devices.talonSrxLiftA.setSelectedSensorPosition(0, TalonUtils.PID_SLOT_0, TalonUtils.TIMEOUT_MS);
            SensorCollection sensorCol = Devices.talonSrxLiftA.getSensorCollection();
            int absolutePosition = sensorCol.getPulseWidthPosition();
            absolutePosition &= 0xFFF;
            if (SENSOR_PHASE) absolutePosition *= -1;
            if (MOTOR_INVERT) absolutePosition *= -1;
            // Set the quadrature (relative) sensor to match absolute
            Devices.talonSrxLiftA.setSelectedSensorPosition(absolutePosition, TalonUtils.PID_LOOP_PRIMARY, TalonUtils.TIMEOUT_MS);
            
            Devices.talonSrxLiftA.configMotionAcceleration(5000, 20);
            Devices.talonSrxLiftA.configMotionCruiseVelocity(10000, 20);

            //Devices.talonSrxLiftB.follow(Devices.talonSrxLiftA);
        }
    }

    @Override
    
    public void initDefaultCommand() {
        Logger.setup("Initializing JuanLift DefaultCommand");
        setDefaultCommand(new LiftStop()); 
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
    
    }

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
        Devices.talonSrxLiftA.set(0.5);
    }

}
