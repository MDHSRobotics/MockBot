
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import java.util.Map;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.Brain;


// The Shuffleboard Inputs Tab
public class InputsTab {

    // Tab, layout, and widget objects
    private ShuffleboardTab m_tab;
    private ShuffleboardLayout m_controllersLayout;
    private ShuffleboardLayout m_xboxLeftLayout;
    private ShuffleboardLayout m_xboxRightLayout;
    private ShuffleboardLayout m_joystickLayout;

    // OI
    private SimpleWidget m_controlStickWidget;

    // Mecanum Drive
    private SimpleWidget m_driveOrientationWidget;
    
    // Thumbstick - Left
    private SimpleWidget m_yLeftDeadZoneWidget;
    private SimpleWidget m_xLeftDeadZoneWidget;
    private SimpleWidget m_yLeftSensitivityWidget;
    private SimpleWidget m_xLeftSensitivityWidget;

    // Thumbstick - Right
    private SimpleWidget m_yRightDeadZoneWidget;
    private SimpleWidget m_xRightDeadZoneWidget;
    private SimpleWidget m_yRightSensitivityWidget;
    private SimpleWidget m_xRightSensitivityWidget;

    // Joystick
    private SimpleWidget m_yDeadZoneWidget;
    private SimpleWidget m_xDeadZoneWidget;
    private SimpleWidget m_zDeadZoneWidget;
    private SimpleWidget m_ySensitivityWidget;
    private SimpleWidget m_xSensitivityWidget;
    private SimpleWidget m_zSensitivityWidget;

    // Constructor
    public InputsTab() {
        ShuffleLogger.logTrivial("Constructing InputsTab...");

        m_tab = Shuffleboard.getTab("Inputs");

        m_controllersLayout = m_tab.getLayout("Controllers", BuiltInLayouts.kGrid);
        m_controllersLayout.withPosition(0, 0);
        m_controllersLayout.withSize(3, 1);
        m_controllersLayout.withProperties(Map.of("Number of columns", 1));
        m_controllersLayout.withProperties(Map.of("Number of rows", 2));
        m_controllersLayout.withProperties(Map.of("Label position", "LEFT"));

        m_xboxLeftLayout = m_tab.getLayout("XBOX Left Thumbstick", BuiltInLayouts.kGrid);
        m_xboxLeftLayout.withPosition(0, 1);
        m_xboxLeftLayout.withSize(3, 1);
        m_xboxLeftLayout.withProperties(Map.of("Number of columns", 2));
        m_xboxLeftLayout.withProperties(Map.of("Number of rows", 2));
        m_xboxLeftLayout.withProperties(Map.of("Label position", "LEFT"));

        m_xboxRightLayout = m_tab.getLayout("XBOX Right Thumbstick", BuiltInLayouts.kGrid);
        m_xboxRightLayout.withPosition(0, 2);
        m_xboxRightLayout.withSize(3, 1);
        m_xboxRightLayout.withProperties(Map.of("Number of columns", 2));
        m_xboxRightLayout.withProperties(Map.of("Number of rows", 2));
        m_xboxRightLayout.withProperties(Map.of("Label position", "LEFT"));

        m_joystickLayout = m_tab.getLayout("Joystick", BuiltInLayouts.kGrid);
        m_joystickLayout.withPosition(0, 3);
        m_joystickLayout.withSize(3, 2);
        m_joystickLayout.withProperties(Map.of("Number of columns", 2));
        m_joystickLayout.withProperties(Map.of("Number of rows", 3));
        m_joystickLayout.withProperties(Map.of("Label position", "LEFT"));
    }

    // Create Brain Widgets
    public void preInitialize() {
        // OI
        m_controlStickWidget = m_controllersLayout.add("Control Stick (XBOX or JOYSTICK)", Brain.controlStickDefault.toString());
        Brain.controlStickEntry = m_controlStickWidget.getEntry();

        // Mecanum Drive
        m_driveOrientationWidget = m_controllersLayout.add("Drive Orientation", Brain.driveOrientationDefault.toString());
        Brain.driveOrientationEntry = m_driveOrientationWidget.getEntry();

        // Thumbstick - Left
        m_yLeftDeadZoneWidget = m_xboxLeftLayout.add("Y Left Dead Zone", Brain.yLeftDeadZoneDefault);
        Brain.yLeftDeadZoneEntry = m_yLeftDeadZoneWidget.getEntry();
        m_yLeftDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_xLeftDeadZoneWidget = m_xboxLeftLayout.add("X Left Dead Zone", Brain.xLeftDeadZoneDefault);
        Brain.xLeftDeadZoneEntry = m_xLeftDeadZoneWidget.getEntry();
        m_xLeftDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_yLeftSensitivityWidget = m_xboxLeftLayout.add("Y Left Sensitivity", Brain.yLeftSensitivityDefault);
        Brain.yLeftSensitivityEntry = m_yLeftSensitivityWidget.getEntry();
        m_yLeftSensitivityWidget.withWidget(BuiltInWidgets.kTextView);

        m_xLeftSensitivityWidget = m_xboxLeftLayout.add("X Left Sensitivity", Brain.xLeftSensitivityDefault);
        Brain.xLeftSensitivityEntry = m_xLeftSensitivityWidget.getEntry();
        m_xLeftSensitivityWidget.withWidget(BuiltInWidgets.kTextView);

        // Thumbstick - Right
        m_yRightDeadZoneWidget = m_xboxRightLayout.add("Y Right Dead Zone", Brain.yRightDeadZoneDefault);
        Brain.yRightDeadZoneEntry = m_yRightDeadZoneWidget.getEntry();
        m_yRightDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_xRightDeadZoneWidget = m_xboxRightLayout.add("X Right Dead Zone", Brain.xRightDeadZoneDefault);
        Brain.xRightDeadZoneEntry = m_xRightDeadZoneWidget.getEntry();
        m_xRightDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_yRightSensitivityWidget = m_xboxRightLayout.add("Y Right Sensitivity", Brain.yRightSensitivityDefault);
        Brain.yRightSensitivityEntry = m_yRightSensitivityWidget.getEntry();
        m_yRightSensitivityWidget.withWidget(BuiltInWidgets.kTextView);

        m_xRightSensitivityWidget = m_xboxRightLayout.add("X Right Sensitivity", Brain.xRightSensitivityDefault);
        Brain.xRightSensitivityEntry = m_xRightSensitivityWidget.getEntry();
        m_xRightSensitivityWidget.withWidget(BuiltInWidgets.kTextView);

        // Joystick
        m_yDeadZoneWidget = m_joystickLayout.add("Y Dead Zone", Brain.yDeadZoneDefault);
        Brain.yDeadZoneEntry = m_yDeadZoneWidget.getEntry();
        m_yDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_xDeadZoneWidget = m_joystickLayout.add("X Dead Zone", Brain.xDeadZoneDefault);
        Brain.xDeadZoneEntry = m_xDeadZoneWidget.getEntry();
        m_xDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_zDeadZoneWidget = m_joystickLayout.add("Z Dead Zone", Brain.zDeadZoneDefault);
        Brain.zDeadZoneEntry = m_zDeadZoneWidget.getEntry();
        m_zDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_ySensitivityWidget = m_joystickLayout.add("Y Sensitivity", Brain.ySensitivityDefault);
        Brain.ySensitivityEntry = m_ySensitivityWidget.getEntry();
        m_ySensitivityWidget.withWidget(BuiltInWidgets.kTextView);

        m_xSensitivityWidget = m_joystickLayout.add("X Sensitivity", Brain.xSensitivityDefault);
        Brain.xSensitivityEntry = m_xSensitivityWidget.getEntry();
        m_xSensitivityWidget.withWidget(BuiltInWidgets.kTextView);

        m_zSensitivityWidget = m_joystickLayout.add("Z Sensitivity", Brain.zSensitivityDefault);
        Brain.zSensitivityEntry = m_zSensitivityWidget.getEntry();
        m_zSensitivityWidget.withWidget(BuiltInWidgets.kTextView);
    }

    // Create all other Widgets
    public void initialize() {
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {
    }

}
