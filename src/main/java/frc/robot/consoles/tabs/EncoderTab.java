
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.Brain;


// The Shuffleboard Sight Tab
public class EncoderTab {

    // Tab, layout, and widget objects
    private ShuffleboardTab m_tab;

    // Encoder Properties
    // private SimpleWidget m_ballTossAngleWidget;

    // private SimpleWidget m_hatchOpenAngleWidget;
    // private SimpleWidget m_hatchCloseAngleWidget;

    // private SimpleWidget m_armHAB2AngleWidget;
    // private SimpleWidget m_armHAB3AngleWidget;
    // private SimpleWidget m_armFullAngleWidget;

    // private SimpleWidget m_pulleyHAB2DistanceWidget;
    // private SimpleWidget m_pulleyHAB3DistanceWidget;

    // Constructor
    public EncoderTab() {
        ShuffleLogger.logTrivial("Constructing EncoderTab...");

        m_tab = Shuffleboard.getTab("Encoders");
    }

    // Create Brain Widgets
    public void preInitialize() {
        
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
