package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;
import Robot.robotjuanclawopen;

// Hatcher subsystem, for grabbing and releasing hatches
public class juanclawopen extends Subsystem {

    // The public property to determine the Hatcher's claw state
    public boolean clawIsClosed = false;

    // Position constants
    private final double GEAR_RATIO = 16;

    // Encoder constants
    private final boolean SENSOR_PHASE = true; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = false; // Which direction you want to be positive; this does not affect motor invert

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

   public juanclawopen() {
        Logger.setup("Constructing Subsystem: juanclawopen...");

        m_talonsAreConnected = Devices.isConnected(Devices.talonSrxjuanclawopen);
        if (!m_talonsAreConnected) {
            Logger.error("juanclawopen talons not all connected! Disabling juanclawopen...");
        }
        else {
            Devices.talonSrxjuanclawopen.configFactoryDefault();

            Devices.talonSrxjuanclawopen.configPeakCurrentDuration(TalonConstants.PEAK_AMPERAGE_DURATION, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxjuanclawopen.configPeakCurrentLimit(TalonConstants.PEAK_AMPERAGE, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxjuanclawopen.configContinuousCurrentLimit(TalonConstants.CONTINUOUS_AMPERAGE_LIMIT, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxjuanclawopen.configNominalOutputForward(0);
            Devices.talonSrxjuanclawopen.configNominalOutputReverse(0);
            Devices.talonSrxjuanclawopen.configPeakOutputForward(0.5);
            Devices.talonSrxjuanclawopen.configPeakOutputReverse(-0.5);

            Devices.talonSrxjuanclawopen.configMotionAcceleration(3000, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxjuanclawopen.configMotionCruiseVelocity(8000, TalonConstants.TIMEOUT_MS);

            // Config TalonSRX Redline encoder
            Devices.talonSrxjuanclawopen.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxjuanclawopen.setSensorPhase(SENSOR_PHASE);
            Devices.talonSrxjuanclawopen.setInverted(MOTOR_INVERT);
            Devices.talonSrxjuanclawopen.configAllowableClosedloopError(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxjuanclawopen.config_kF(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxjuanclawopen.config_kP(TalonConstants.PID_LOOP_PRIMARY, 0.32, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxjuanclawopen.config_kI(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxjuanclawopen.config_kD(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);

            // Initialize current encoder position as zero 
            Devices.talonSrxjuanclawopen.setSelectedSensorPosition(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            SensorCollection sensorCol = Devices.talonSrxjuanclawopen.getSensorCollection();
            int absolutePosition = sensorCol.getPulseWidthPosition();
            absolutePosition &= 0xFFF;
            if (SENSOR_PHASE) absolutePosition *= -1;
            if (MOTOR_INVERT) absolutePosition *= -1;
            // Set the quadrature (relative) sensor to match absolute
            Devices.talonSrxjuanclawopen.setSelectedSensorPosition(absolutePosition, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
        }
    }

// To do: Button-Map commands for opening/closing bumpers and toggling wheels
// to do: claw close: public static final JoystickButton climbXboxBtnBumperLeft = new JoystickButton(climbXbox, 5);
// to do: claw open: public static final JoystickButton climbXboxBtnBumperRight = new JoystickButton(climbXbox, 6);
// to do: wheels on/off: public static final JoystickButton climbXboxBtnA = new JoystickButton(climbXbox, 1);


public class juanclawopen extends Command {

    public juanclawopen() {
        Logger.setup("Constructing Command: juanclawopen...");

        // Declare subsystem dependencies
        requires(Robot.robotjuanclawopen);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: juanclawopen...");
    }

    @Override
    protected void execute() {
        Robot.robotjuanclawopen.resetPosition();
    }

    // This command finishes immediately, but is intended to be continually restarted while a button is held
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: juanclawopen...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: juanclawopen...");

        Robot.robotjuanclawopen.stop();
    }

}