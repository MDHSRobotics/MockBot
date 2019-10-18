

package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;
import Robot.robotJuanClaw;

// JuanClaw subsystem, for grabbing and releasing claw
public class JuanClaw extends Subsystem {

    // The public property to determine the JuanClaw's claw state
    public boolean clawIsClosed = false;

    // Position constants
    private final double GEAR_RATIO = 16;

    // Encoder constants
    private final boolean SENSOR_PHASE = true; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = false; // Which direction you want to be positive; this does not affect motor invert

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public JuanClaw() {
        Logger.setup("Constructing Subsystem: JuanClaw...");

        m_talonsAreConnected = Devices.isConnected(Devices.talonSrxJuanClaw);
        if (!m_talonsAreConnected) {
            Logger.error("JuanClaw talons not all connected! Disabling JuanClaw...");
        }
        else {
            Devices.talonSrxJuanClaw.configFactoryDefault();

            Devices.talonSrxJuanClaw.configPeakCurrentDuration(TalonConstants.PEAK_AMPERAGE_DURATION, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxJuanClaw.configPeakCurrentLimit(TalonConstants.PEAK_AMPERAGE, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxJuanClaw.configContinuousCurrentLimit(TalonConstants.CONTINUOUS_AMPERAGE_LIMIT, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxJuanClaw.configNominalOutputForward(0);
            Devices.talonSrxJuanClaw.configNominalOutputReverse(0);
            Devices.talonSrxJuanClaw.configPeakOutputForward(0.5);
            Devices.talonSrxJuanClaw.configPeakOutputReverse(-0.5);

            Devices.talonSrxJuanClaw.configMotionAcceleration(3000, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxJuanClaw.configMotionCruiseVelocity(8000, TalonConstants.TIMEOUT_MS);

            // Config TalonSRX Redline encoder
            Devices.talonSrxJuanClaw.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxJuanClaw.setSensorPhase(SENSOR_PHASE);
            Devices.talonSrxJuanClaw.setInverted(MOTOR_INVERT);
            Devices.talonSrxJuanClaw.configAllowableClosedloopError(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);

            Devices.talonSrxJuanClaw.config_kF(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxJuanClaw.config_kP(TalonConstants.PID_LOOP_PRIMARY, 0.32, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxJuanClaw.config_kI(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
            Devices.talonSrxJuanClaw.config_kD(TalonConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);

            // Initialize current encoder position as zero 
            Devices.talonSrxJuanClaw.setSelectedSensorPosition(0, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
            SensorCollection sensorCol = Devices.talonSrxJuanClaw.getSensorCollection();
            int absolutePosition = sensorCol.getPulseWidthPosition();
            absolutePosition &= 0xFFF;
            if (SENSOR_PHASE) absolutePosition *= -1;
            if (MOTOR_INVERT) absolutePosition *= -1;
            // Set the quadrature (relative) sensor to match absolute
            Devices.talonSrxJuanClaw.setSelectedSensorPosition(absolutePosition, TalonConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
        }
    }

// To do: Button-Map commands for opening/closing bumpers and toggling wheels
// to do: claw close: public static final JoystickButton climbXboxBtnBumperLeft = new JoystickButton(climbXbox, 5);
// to do: claw open: public static final JoystickButton climbXboxBtnBumperRight = new JoystickButton(climbXbox, 6);
// to do: wheels on/off: public static final JoystickButton climbXboxBtnA = new JoystickButton(climbXbox, 1);


public class JuanClaw extends Command {

    public JuanClaw() {
        Logger.setup("Constructing Command: JuanClaw...");

        // Declare subsystem dependencies
        requires(Robot.robotJuanClaw);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: JuanClaw...");
    }

    @Override
    protected void execute() {
        Robot.robotJuanClaw.resetPosition();
    }

    // This command finishes immediately, but is intended to be continually restarted while a button is held
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: JuanClaw...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: JuanClaw...");

        Robot.robotJuanClaw.stop();
    }

}