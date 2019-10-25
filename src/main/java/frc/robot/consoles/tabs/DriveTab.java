
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

// import frc.robot.commands.test.MecDriveStraightDistance;
import frc.robot.consoles.ShuffleLogger;
import frc.robot.Brain;
import frc.robot.Devices;
// import frc.robot.Robot;
import frc.robot.subsystems.MecDriver;


// The Shuffleboard Drive Tab
public class DriveTab {

    // Tab, layout, and widget objects
    private ShuffleboardTab m_tab;
    private ComplexWidget m_mecDriveWidget;
    // private ComplexWidget m_mecDriverWidget;
    // private ComplexWidget m_mecDriveStraightCmdWidget;
    // private SimpleWidget m_targetDistanceWidget;
    private SimpleWidget m_alignFrontMagnitudeWidget;
    private SimpleWidget m_alignSideMagnitudeWidget;
    private SimpleWidget m_alignZSensitivityWidget;
    private SimpleWidget m_alignZSpeedMinimumWidget;
    private SimpleWidget m_alignZToleranceWidget;

    // Create Brain Widgets
    public DriveTab() {
        ShuffleLogger.logCritical("Constructing DriveTab...");

        m_tab = Shuffleboard.getTab("Drive");
    }

    // Create Brain Widgets
    public void preInitialize() {
        // Target Distance for Drive Forward command
        // m_targetDistanceWidget = m_tab.add("Target Distance", Brain.driveTargetDistanceDefault);
        // Brain.driveTargetDistanceEntry = m_targetDistanceWidget.getEntry();

        m_alignFrontMagnitudeWidget = m_tab.add("Front Magnitude", Brain.alignFrontMagnitudeDefault);
        Brain.alignFrontMagnitudeEntry = m_alignFrontMagnitudeWidget.getEntry();

        m_alignSideMagnitudeWidget = m_tab.add("Side Magnitude", Brain.alignSideMagnitudeDefault);
        Brain.alignSideMagnitudeEntry = m_alignSideMagnitudeWidget.getEntry();

        m_alignZSensitivityWidget = m_tab.add("Z Sensitivity", Brain.alignZSensitivityDefault);
        Brain.alignZSensitivityEntry = m_alignZSensitivityWidget.getEntry();

        m_alignZSpeedMinimumWidget = m_tab.add("Z Speed Minimum", Brain.alignZSpeedMinimumDefault);
        Brain.alignZSpeedMinimumEntry = m_alignZSpeedMinimumWidget.getEntry();

        m_alignZToleranceWidget = m_tab.add("Z Tolerance", Brain.alignZToleranceDefault);
        Brain.alignZToleranceEntry = m_alignZToleranceWidget.getEntry();
    }

    // Create all other Widgets
    public void initialize() {
        m_mecDriveWidget = m_tab.add("Mecanum Drive", MecDriver.mecDrive);
        // m_mecDriverWidget = m_tab.add("Mecanum Driver Subsystem", Robot.robotMecDriver);
        // m_mecDriveStraightCmdWidget = m_tab.add("Mecanum Drive Straight", new MecDriveStraightDistance());
    }

    // Configure all Widgets
    public void configure() {
        // m_mecDriverWidget.withPosition(0, 0);
        // m_mecDriverWidget.withSize(2, 1);

        // m_targetDistanceWidget.withPosition(0, 1);
        // m_targetDistanceWidget.withWidget(BuiltInWidgets.kTextView);

        // m_mecDriveStraightCmdWidget.withPosition(0, 2);
        // m_mecDriveStraightCmdWidget.withSize(2, 1);

        m_mecDriveWidget.withPosition(3, 1);
        m_mecDriveWidget.withSize(4, 3);

        m_alignFrontMagnitudeWidget.withPosition(0, 0);
        m_alignSideMagnitudeWidget.withPosition(1, 0);
        m_alignZSensitivityWidget.withPosition(2, 0);
        m_alignZSpeedMinimumWidget.withPosition(0, 1);
        m_alignZToleranceWidget.withPosition(1, 1);
    }

    // This will be called in the robotPeriodic
    public void update() {
    }

}
