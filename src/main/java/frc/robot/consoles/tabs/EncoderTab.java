
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.Brain;


// The Shuffleboard Sight Tab
public class EncoderTab {

    // Tab, layout, and widget objects
    private ShuffleboardTab m_tab;

    // Encoder Properties
    private SimpleWidget m_ballTossAngleWidget;

    private SimpleWidget m_hatchOpenAngleWidget;
    private SimpleWidget m_hatchCloseAngleWidget;

    private SimpleWidget m_armHAB2AngleWidget;
    private SimpleWidget m_armHAB3AngleWidget;
    private SimpleWidget m_armFullAngleWidget;

    private SimpleWidget m_pulleyHAB2DistanceWidget;
    private SimpleWidget m_pulleyHAB3DistanceWidget;

    // Constructor
    public EncoderTab() {
        ShuffleLogger.logTrivial("Constructing EncoderTab...");

        m_tab = Shuffleboard.getTab("Encoders");
    }

    // Create Brain Widgets
    public void preInitialize() {
        m_ballTossAngleWidget = m_tab.add("Ball Toss Angle", Brain.ballTossAngleDefault);
        Brain.ballTossAngleEntry = m_ballTossAngleWidget.getEntry();

        m_hatchOpenAngleWidget = m_tab.add("Hatch Open Angle", Brain.hatchOpenAngleDefault);
        Brain.hatchOpenAngleEntry = m_hatchOpenAngleWidget.getEntry();

        m_hatchCloseAngleWidget = m_tab.add("Hatch Close Ang.", Brain.hatchCloseAngleDefault);
        Brain.hatchCloseAngleEntry = m_hatchCloseAngleWidget.getEntry();

        m_armHAB2AngleWidget = m_tab.add("Arm HAB2 Angle", Brain.armHAB2AngleDefault);
        Brain.armHAB2AngleEntry = m_armHAB2AngleWidget.getEntry();

        m_armHAB3AngleWidget = m_tab.add("Arm HAB3 Angle", Brain.armHAB3AngleDefault);
        Brain.armHAB3AngleEntry = m_armHAB3AngleWidget.getEntry();

        m_armFullAngleWidget = m_tab.add("Arm Full Angle", Brain.armFullAngleDefault);
        Brain.armFullAngleEntry = m_armFullAngleWidget.getEntry();

        m_pulleyHAB2DistanceWidget = m_tab.add("Pulley HAB2 Dist.", Brain.pulleyHAB2DistanceDefault);
        Brain.pulleyHAB2DistanceEntry = m_pulleyHAB2DistanceWidget.getEntry();

        m_pulleyHAB3DistanceWidget = m_tab.add("Pulley HAB3 Dist.", Brain.pulleyHAB3DistanceDefault);
        Brain.pulleyHAB3DistanceEntry = m_pulleyHAB3DistanceWidget.getEntry();
    }

    // Create all other Widgets
    public void initialize() {
    }

    // Configure all Widgets
    public void configure() {
        m_ballTossAngleWidget.withWidget(BuiltInWidgets.kTextView);
        m_ballTossAngleWidget.withPosition(0, 0);

        m_hatchOpenAngleWidget.withWidget(BuiltInWidgets.kTextView);
        m_hatchOpenAngleWidget.withPosition(1, 0);

        m_hatchCloseAngleWidget.withWidget(BuiltInWidgets.kTextView);
        m_hatchCloseAngleWidget.withPosition(2, 0);

        m_pulleyHAB2DistanceWidget.withWidget(BuiltInWidgets.kTextView);
        m_pulleyHAB2DistanceWidget.withPosition(0, 1);
        

        m_pulleyHAB3DistanceWidget.withWidget(BuiltInWidgets.kTextView);
        m_pulleyHAB3DistanceWidget.withPosition(1, 1);

        m_armHAB2AngleWidget.withWidget(BuiltInWidgets.kTextView);
        m_armHAB2AngleWidget.withPosition(0, 2);

        m_armHAB3AngleWidget.withWidget(BuiltInWidgets.kTextView);
        m_armHAB3AngleWidget.withPosition(1, 2);

        m_armFullAngleWidget.withWidget(BuiltInWidgets.kTextView);
        m_armFullAngleWidget.withPosition(2, 2);
    }

    // This will be called in the robotPeriodic
    public void update() {
    }

}
