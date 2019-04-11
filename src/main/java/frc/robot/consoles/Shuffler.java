
package frc.robot.consoles;

import frc.robot.consoles.tabs.*;


// Class that wraps all of the interaction with the Shuffleboard

// All decisions about content and layout of the Shuffleboard are consolidated in this file
// to make it easier to change things rather than having to look throughout all of the 
// classes for subsystems, commands, etc.

// The Shuffler class knows about the subsystems, commands, etc. but generally not vice versa
public class Shuffler {

    // Tabs
    private MainTab m_mainTab;
    private SightTab m_sightTab;
    private VisionTab m_visionTab;
    private InputsTab m_inputsTab;
    private EncoderTab m_encoderTab;
    private DriveTab m_driveTab;
    private DebugTab m_debugTab;

    public Shuffler() {
        ShuffleLogger.logTrivial("Constructing Shuffler...");

        m_mainTab = new MainTab();
        m_sightTab = new SightTab();
        m_visionTab = new VisionTab();
        m_inputsTab = new InputsTab();
        m_encoderTab = new EncoderTab();
        m_driveTab = new DriveTab();
        m_debugTab = new DebugTab();
    }

    public void preInitialize() {
        ShuffleLogger.logTrivial("Pre-Initializing Shuffler...");

        m_mainTab.preInitialize();
        m_sightTab.preInitialize();
        m_visionTab.preInitialize();
        m_inputsTab.preInitialize();
        m_encoderTab.preInitialize();
        m_driveTab.preInitialize();
        m_debugTab.preInitialize();
    }

    public void initialize() {
        ShuffleLogger.logTrivial("Initializing Shuffler...");

        m_mainTab.initialize();
        m_sightTab.initialize();
        m_visionTab.initialize();
        m_inputsTab.initialize();
        m_encoderTab.initialize();
        m_driveTab.initialize();
        m_debugTab.initialize();
    }

    public void configure() {
        ShuffleLogger.logTrivial("Configuring Shuffler...");

        m_mainTab.configure();
        m_sightTab.configure();
        m_visionTab.configure();
        m_inputsTab.configure();
        m_encoderTab.configure();
        m_driveTab.configure();
        m_debugTab.configure();

        setupSmartdashboard();
    }

    public void update() {
        m_mainTab.update();
        m_sightTab.update();
        m_visionTab.update();
        m_inputsTab.update();
        m_encoderTab.update();
        m_driveTab.update();
        m_debugTab.update();
    }

    // This is for stuff that can't be displayed easily in custom Shuffleboard tabs
    // Will end up on the SmartDashboard tab
    private void setupSmartdashboard() {

        // SmartDashboard.putData("Command Scheduler",Scheduler.getInstance());

    }

}
