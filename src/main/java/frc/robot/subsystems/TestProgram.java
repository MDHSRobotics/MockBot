
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.idle.TestProgramStop;
import frc.robot.consoles.Logger;
import frc.robot.helpers.TalonUtils;
import frc.robot.Brain;
import frc.robot.Devices;


// TestProgram subsystem, for testing the new electrical boards and motors
public class TestProgram extends Subsystem {

    // The public property to determine the TestProgram's rotation state
    public boolean rotationIsBackward = true;

    // Position constants
    private final double GEAR_RATIO = 16;
    private final double ROTATIONS = 10;

    // Encoder constants
    private final boolean SENSOR_PHASE = true; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = false; // Which direction you want to be positive; this does not affect motor invert

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public TestProgram() {
        Logger.setup("Constructing Subsystem: TestProgram...");

        m_talonsAreConnected = Devices.isConnected(Devices.talonTestBoard);
        if (!m_talonsAreConnected) {
            Logger.error("TestProgram talons not all connected! Disabling TestProgram...");
        }
        else {
            Devices.talonTestBoard.configFactoryDefault();

            Devices.talonTestBoard.configPeakCurrentDuration(TalonUtils.PEAK_AMPERAGE_DURATION, TalonUtils.TIMEOUT_MS);
            Devices.talonTestBoard.configPeakCurrentLimit(TalonUtils.PEAK_AMPERAGE, TalonUtils.TIMEOUT_MS);
            Devices.talonTestBoard.configContinuousCurrentLimit(TalonUtils.CONTINUOUS_AMPERAGE_LIMIT, TalonUtils.TIMEOUT_MS);

            Devices.talonTestBoard.configNominalOutputForward(0);
            Devices.talonTestBoard.configNominalOutputReverse(0);
            Devices.talonTestBoard.configPeakOutputForward(0.5);
            Devices.talonTestBoard.configPeakOutputReverse(-0.5);

            Devices.talonTestBoard.configMotionAcceleration(3000, TalonUtils.TIMEOUT_MS);
            Devices.talonTestBoard.configMotionCruiseVelocity(8000, TalonUtils.TIMEOUT_MS);
            Devices.talonTestBoard.configMotionSCurveStrength(4, TalonUtils.TIMEOUT_MS);

            // Config TalonSRX Redline encoder
            Devices.talonTestBoard.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TalonUtils.PID_LOOP_PRIMARY, TalonUtils.TIMEOUT_MS);
            Devices.talonTestBoard.setSensorPhase(SENSOR_PHASE);
            Devices.talonTestBoard.setInverted(MOTOR_INVERT);
            Devices.talonTestBoard.configAllowableClosedloopError(0, TalonUtils.PID_LOOP_PRIMARY, TalonUtils.TIMEOUT_MS);

            Devices.talonTestBoard.config_kF(TalonUtils.PID_LOOP_PRIMARY, 0.0, TalonUtils.TIMEOUT_MS);
            Devices.talonTestBoard.config_kP(TalonUtils.PID_LOOP_PRIMARY, 0.32, TalonUtils.TIMEOUT_MS);
            Devices.talonTestBoard.config_kI(TalonUtils.PID_LOOP_PRIMARY, 0.0, TalonUtils.TIMEOUT_MS);
            Devices.talonTestBoard.config_kD(TalonUtils.PID_LOOP_PRIMARY, 0.0, TalonUtils.TIMEOUT_MS);

            // Initialize current encoder position as zero 
            Devices.talonTestBoard.setSelectedSensorPosition(0, TalonUtils.PID_LOOP_PRIMARY, TalonUtils.TIMEOUT_MS);
            SensorCollection sensorCol = Devices.talonTestBoard.getSensorCollection();
            int absolutePosition = sensorCol.getPulseWidthPosition();
            absolutePosition &= 0xFFF;
            if (SENSOR_PHASE) absolutePosition *= -1;
            if (MOTOR_INVERT) absolutePosition *= -1;
            // Set the quadrature (relative) sensor to match absolute
            Devices.talonTestBoard.setSelectedSensorPosition(absolutePosition, TalonUtils.PID_LOOP_PRIMARY, TalonUtils.TIMEOUT_MS);
        }
    }

    @Override
    public void initDefaultCommand() {
        Logger.setup("Initializing TestProgram DefaultCommand -> TestProgramStop...");

        setDefaultCommand(new TestProgramStop());
    }

    // Toggle the rotationIsBackward state
    public void togglePosition() {
        rotationIsBackward = !rotationIsBackward;
    }

    // Stop the TestProgram motor
    public void stop() {
        if (!m_talonsAreConnected) return;
        Devices.talonTestBoard.stopMotor();
    }

    // Runs motor at a static power percentage
    public void testMotorDry() {
        if (!m_talonsAreConnected) return;
        Devices.talonTestBoard.set(0.5);
        Logger.info("TestProgram -> Running motor at 50% power");
    }

    // Rotates motor forward to designated position
    public void testMotorEncoderForward() {
        double ticks = TalonUtils.translateRotationsToTicks(ROTATIONS, GEAR_RATIO);
        Logger.info("TestProgram -> Motion Magic to FORWARD: " + ROTATIONS + " rotations, " + ticks + " ticks");

        if (!m_talonsAreConnected) return;
        Devices.talonTestBoard.set(ControlMode.MotionMagic, ticks);
    }

    // Rotates motor back to original position
    public void testMotorEncoderBackward() {
        Logger.info("TestProgram -> Motion Magic to BACKWARD: " + -ROTATIONS);

        if (!m_talonsAreConnected) return;
        Devices.talonTestBoard.set(ControlMode.MotionMagic, 0);
    }

    // Get the current encoder motor velocity
    public int getVelocity() {
        if (!m_talonsAreConnected) return 0;
        return Devices.talonTestBoard.getSelectedSensorVelocity();
    }

    // Get the current encoder motor position
    public int getPosition() {
        if (!m_talonsAreConnected) return 0;
        return Devices.talonTestBoard.getSelectedSensorPosition();
    }

}