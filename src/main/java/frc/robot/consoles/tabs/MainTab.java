
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import java.util.Map;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.sensors.Vision;
import frc.robot.Brain;
import frc.robot.Robot;


// The Shuffleboard Main Tab
public class MainTab {

    // Tab, layout, and widget objects
    private ShuffleboardTab m_tab;
    private ComplexWidget m_autoCmdWidget;
    private SimpleWidget m_matchTimeWidget;
    private SimpleWidget m_frontLineDetectedWidget;
    private SimpleWidget m_gameModeWidget;
    private SimpleWidget m_climbModeWidget;

    // Constructor
    public MainTab() {
        ShuffleLogger.logTrivial("Constructing MainTab...");

        m_tab = Shuffleboard.getTab("Main");
    }

    // Create Brain Widgets
    public void preInitialize() {
        // Match Time
        m_matchTimeWidget = m_tab.add("Match Time", Brain.matchTimeDefault);
        Brain.matchTimeEntry = m_matchTimeWidget.getEntry();

        // Front Line Detected
        m_frontLineDetectedWidget = m_tab.add("Front Line Detected", Brain.frontLineDetectedDefault);
        Brain.frontLineDetectedEntry = m_frontLineDetectedWidget.getEntry();

        m_gameModeWidget = m_tab.add("Game Mode", Robot.robotGameMode.toString());
    }

    // Create all other Widgets
    public void initialize() {
        // Autonomous Command
        m_autoCmdWidget = m_tab.add("Auto Command", Robot.autoCommandChooser);
    }

    // Configure all Widgets
    public void configure() {
        m_autoCmdWidget.withPosition(0, 0);
        m_autoCmdWidget.withSize(2, 1);

        m_matchTimeWidget.withPosition(2, 0);
        m_matchTimeWidget.withWidget(BuiltInWidgets.kDial);
        m_matchTimeWidget.withProperties(Map.of("min", -1, "max", 135)); // this property setting isn't working

        m_frontLineDetectedWidget.withPosition(3, 0);

        m_gameModeWidget.withPosition(0, 2);
    }

    // This will be called in the robotPeriodic
    public void update() {
        // Match time
        DriverStation ds = DriverStation.getInstance();
        double matchTime = ds.getMatchTime();
        Brain.matchTimeEntry.setDouble(matchTime);

        // Front Line Detector
        boolean frontLineDetected = Vision.frontLineDetected();
        Brain.frontLineDetectedEntry.setBoolean(frontLineDetected);

        // updating the value of the game mode
        NetworkTableEntry gameModeEntry = m_gameModeWidget.getEntry();
        gameModeEntry.setString(Robot.robotGameMode.toString());
    }

}
