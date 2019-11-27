
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import frc.robot.consoles.*;
import frc.robot.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    // Robot States
    public enum Variant {
        TEST_BOARD, TEST_DRIVE, BUILD_HOME, BUILD_AWAY
    }

    public enum GameMode {
        DELIVERY, CLIMB
    }

    public enum DeliveryMode {
        GET_HATCH, ATTACH_HATCH, GET_BALL, TOSS_BALL
    }

    public enum ClimbMode {
        HAB2, HAB3
    }

    // Variant is used to configure different device mappings for different "robots"
    // TODO: This needs to be added to the Brain and Shuffleboard, so that it is settable on the fly
    public static Variant robotVariant = Variant.TEST_BOARD;
    // Game Mode is used to activate/deactivate the Climb Xbox Controller
    public static GameMode robotGameMode = GameMode.DELIVERY;
    // Delivery Mode is used to control vision processing actions, as well as xbox controller activation
    // TODO: We need to implement ways to set the Robot DeliveryMode, either manually, or automatically, or a combination
    // TODO: Determine the best default. What's the first action the Robot will take during Sandstorm?
    public static DeliveryMode robotDeliveryMode = DeliveryMode.GET_HATCH;
    // Climb Mode tells the climb commands which system needs to be activated next
    public static ClimbMode robotClimbMode = ClimbMode.HAB2;

    // Core Classes
    public static Logger robotLogger;
    public static Devices robotDevices;

    // Subsystems
    public static TestPneumatic testPneumatic;

    // Consoles
    public static SendableChooser<Command> autoCommandChooser;
    private Command m_autoCmd;

    // OI
    public static OI robotOI;
    public boolean driveXBoxConnected = false;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        System.out.println("--");

        // Instantiate the Logger FIRST
        robotLogger = new Logger();
        Logger.setup("Initializing Robot...");

        // Instantiate Devices SECOND
        robotDevices = new Devices();
        
        // Ensure that the gyro is connected
        if (!Devices.gyro.isConnected()) Logger.error("Gyro not connected!");

        // Instantiate Subsystems FIFTH
        testPneumatic = new TestPneumatic();

        // Intialize and configure the shuffler, and instantiate OI, in that order, LAST
        robotOI = new OI();

        // Check which controllers are plugged in
        driveXBoxConnected = Devices.isDriveXboxConnected();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {

        // Detect whether a controller has been plugged in after start-up
        if (!driveXBoxConnected) {
            if (Devices.isDriveXboxConnected()) {
                // Drive XBox was not previously plugged in but now it is so set up buttons
                OI.configureDriveXBoxButtons();
                Logger.setup("Drive XBox controller detected and configured");
                driveXBoxConnected = true;
            }
        }
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
        System.out.println("--");
        Logger.ending("Disabling Robot...");
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        System.out.println("--");
        Logger.action("Initializing Autonomous...");

        m_autoCmd = autoCommandChooser.getSelected();

        /*
        String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
        switch (autoSelected) {
            case "My Auto":
                autonomousCommand = new MyAutoCommand();
                break;
            case "Default Auto":
            default:
                autonomousCommand = new ExampleCommand();
                break;
        }
        */

        // Schedule the autonomous command
        if (m_autoCmd != null) {
            m_autoCmd.start();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        System.out.println("--");
        Logger.action("Initializing Teleop...");

        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autoCmd != null) {
            m_autoCmd.cancel();
        }

    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

}
