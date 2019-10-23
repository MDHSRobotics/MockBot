
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.Brain;


// The Shuffleboard Sight Tab
public class EncoderTab {

    // Tab, layout, and widget objects
    private ShuffleboardTab m_tab;

    // Encoder Properties
    private SimpleWidget m_LiftPulleyLiftDistanceWidget;

    // Constructor
    public EncoderTab() {
        ShuffleLogger.logTrivial("Constructing EncoderTab...");

        m_tab = Shuffleboard.getTab("Encoders");
    }

    // Create Brain Widgets
    public void preInitialize() {
        m_LiftPulleyLiftDistanceWidget = m_tab.add("LiftPulley Dist.", Brain.liftPulleyDistanceDefault);
        Brain.liftPulleyDistanceEntry = m_LiftPulleyLiftDistanceWidget.getEntry();
    }

    // Create all other Widgets
    public void initialize() {
    }

    // Configure all Widgets
    public void configure() {
        m_LiftPulleyLiftDistanceWidget.withWidget(BuiltInWidgets.kTextView);
        m_LiftPulleyLiftDistanceWidget.withPosition(0, 0);
    }

    // This will be called in the robotPeriodic
    public void update() {
    }

}
