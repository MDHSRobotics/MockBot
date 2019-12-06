
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

import frc.robot.helpers.DPad;
import frc.robot.helpers.DPadButton;
import frc.robot.helpers.DPadButton.Direction;


// This class contains singleton constants, for human interface devices and robot components, and mappings for each
public class Devices {
    
    static private final int DRIVE_XBOX_STICK_NUM = 0;
    static private final int JSTICK_STICK_NUM = 2;

    // Joysticks
    public static final Joystick jstick = new Joystick(JSTICK_STICK_NUM);
    public static final JoystickButton jstickBtn1 = new JoystickButton(jstick, 1); // Trigger
    public static final JoystickButton jstickBtn2 = new JoystickButton(jstick, 2);
    public static final JoystickButton jstickBtn3 = new JoystickButton(jstick, 3);
    public static final JoystickButton jstickBtn4 = new JoystickButton(jstick, 4);
    public static final JoystickButton jstickBtn5 = new JoystickButton(jstick, 5);
    public static final JoystickButton jstickBtn6 = new JoystickButton(jstick, 6);
    public static final JoystickButton jstickBtn7 = new JoystickButton(jstick, 7);
    public static final JoystickButton jstickBtn8 = new JoystickButton(jstick, 8);
    public static final JoystickButton jstickBtn9 = new JoystickButton(jstick, 9);
    public static final JoystickButton jstickBtn10 = new JoystickButton(jstick, 10);
    public static final JoystickButton jstickBtn11 = new JoystickButton(jstick, 11);
    public static final JoystickButton jstickBtn12 = new JoystickButton(jstick, 12);

    // Xbox Controller - Drive & Delivery
    public static final XboxController driveXbox = new XboxController(DRIVE_XBOX_STICK_NUM);
    public static final JoystickButton driveXboxBtnA = new JoystickButton(driveXbox, 1);
    public static final JoystickButton driveXboxBtnB = new JoystickButton(driveXbox, 2);
    public static final JoystickButton driveXboxBtnX = new JoystickButton(driveXbox, 3);
    public static final JoystickButton driveXboxBtnY = new JoystickButton(driveXbox, 4);
    public static final JoystickButton driveXboxBtnBumperLeft = new JoystickButton(driveXbox, 5);
    public static final JoystickButton driveXboxBtnBumperRight = new JoystickButton(driveXbox, 6);
    public static final JoystickButton driveXboxBtnBack = new JoystickButton(driveXbox, 7);
    public static final JoystickButton driveXboxBtnStart = new JoystickButton(driveXbox, 8);
    public static final JoystickButton driveXboxBtnStickLeft = new JoystickButton(driveXbox, 9);
    public static final JoystickButton driveXboxBtnStickRight = new JoystickButton(driveXbox, 10);
    public static final DPad driveXboxBtnDpad = new DPad(driveXbox);
    public static final DPadButton driveXboxBtnDpadUp = new DPadButton(driveXbox, Direction.UP);
    public static final DPadButton driveXboxBtnDpadDown = new DPadButton(driveXbox, Direction.DOWN);
    public static final DPadButton driveXboxBtnDpadLeft = new DPadButton(driveXbox, Direction.LEFT);
    public static final DPadButton driveXboxBtnDpadRight = new DPadButton(driveXbox, Direction.RIGHT);
    public static final DPadButton driveXboxBtnDpadUpLeft = new DPadButton(driveXbox, Direction.UP_LEFT);
    public static final DPadButton driveXboxBtnDpadUpRight = new DPadButton(driveXbox, Direction.UP_RIGHT);
    public static final DPadButton driveXboxBtnDpadDownLeft = new DPadButton(driveXbox, Direction.DOWN_LEFT);
    public static final DPadButton driveXboxBtnDpadDownRight = new DPadButton(driveXbox, Direction.DOWN_RIGHT);

    // Gyros
    public static final AHRS gyro = new AHRS(SPI.Port.kMXP);

    // Relays
    public static final Relay lighterRelay = new Relay(1);

    // Pneumatics Controller
    public static final Compressor pcm = new Compressor(0);

    // Solenoids
    public static final Solenoid testSolenoid = new Solenoid(0);
    public static final Solenoid testSolenoid2 = new Solenoid(1);
    public static final DoubleSolenoid testDoubleSolenoid = new DoubleSolenoid(5, 6);


    // Drives
    public static MecanumDrive mecDrive = null;

    // Constructor
    public Devices() {

    }

    // Determines if the Talon SRX is connected
    public static boolean isConnected(WPI_TalonSRX talon) {
        int firmVer = talon.getFirmwareVersion();
        boolean connected = (firmVer != -1);
        return connected;
    }

    // Determine if a given stick is connected
    public static boolean isStickConnected(int stickNumber) {
        int numberOfButtons = DriverStation.getInstance().getStickButtonCount(stickNumber);
        return numberOfButtons > 0;
    }

    // Determine if the Drive XBox controller is connected
    public static boolean isDriveXboxConnected() {
        return isStickConnected(DRIVE_XBOX_STICK_NUM);
    }

}
