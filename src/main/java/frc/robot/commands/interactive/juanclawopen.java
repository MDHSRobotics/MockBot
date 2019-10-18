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