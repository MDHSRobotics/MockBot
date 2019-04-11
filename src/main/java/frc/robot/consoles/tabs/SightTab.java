
package frc.robot.consoles.tabs;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.Brain;
import frc.robot.Robot;


// The Shuffleboard Sight Tab
public class SightTab {

    // Tab, layout, and widget objects
    private ShuffleboardTab m_tab;

    // Sight Properties
    private SimpleWidget m_brightnessWidget;
    private SimpleWidget m_exposureWidget;
    private SimpleWidget m_whiteBalanceWidget;

    // Constructor
    public SightTab() {
        ShuffleLogger.logTrivial("Constructing SightTab...");

        m_tab = Shuffleboard.getTab("Sight");
    }

    // Create Brain Widgets
    public void preInitialize() {
        m_brightnessWidget = m_tab.add("Brightness", Brain.brightnessDefault);
        Brain.brightnessEntry = m_brightnessWidget.getEntry();

        m_exposureWidget = m_tab.add("Exposure", Brain.exposureDefault);
        Brain.exposureEntry = m_exposureWidget.getEntry();

        m_whiteBalanceWidget = m_tab.add("White Balance", Brain.whiteBalanceDefault);
        Brain.whiteBalanceEntry = m_whiteBalanceWidget.getEntry();
    }

    // Create all other Widgets
    public void initialize() {
    }

    // Configure all Widgets
    public void configure() {
        m_brightnessWidget.withWidget(BuiltInWidgets.kTextView);
        m_exposureWidget.withWidget(BuiltInWidgets.kTextView);
        m_whiteBalanceWidget.withWidget(BuiltInWidgets.kTextView);
    }

    // This will be called in the robotPeriodic
    public void update() {

        // Don't need to update anything if the sight camera is not active
        if (Robot.robotCameraSight == null) return;

        double brightness = Brain.getBrightness();
        NetworkTableEntry brightnessEntry = m_brightnessWidget.getEntry();
        double newBrightness = brightnessEntry.getDouble(brightness);
        if (newBrightness != brightness) {
            Robot.robotCameraSight.setBrightness((int)newBrightness);
        }

        double exposure = Brain.getExposure();
        NetworkTableEntry exposureEntry = m_exposureWidget.getEntry();
        double newExposure = exposureEntry.getDouble(brightness);
        if (newExposure != exposure) {
            Robot.robotCameraSight.setExposureManual((int)newExposure);
        }

        double whiteBalance = Brain.getBrightness();
        NetworkTableEntry whiteBalanceEntry = m_whiteBalanceWidget.getEntry();
        double newWhiteBalance = whiteBalanceEntry.getDouble(whiteBalance);
        if (newWhiteBalance != whiteBalance) {
            Robot.robotCameraSight.setWhiteBalanceManual((int)newWhiteBalance);
        }
    }

}
