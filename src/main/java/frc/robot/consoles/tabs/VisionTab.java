
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import java.util.Map;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.Brain;


// The Shuffleboard Vision Tab
public class VisionTab {

    // Tab, layout, and widget objects
    private ShuffleboardTab m_tab;
    private ShuffleboardLayout m_hsvLayout;
    private ShuffleboardLayout m_frontCamLayout;
    private ShuffleboardLayout m_leftCamLayout;
    private ShuffleboardLayout m_rightCamLayout;

    // Line Pipeline - HSV Thresholds
    private SimpleWidget m_hueMinWidget;
    private SimpleWidget m_hueMaxWidget;
    private SimpleWidget m_saturationMinWidget;
    private SimpleWidget m_saturationMaxWidget;
    private SimpleWidget m_valueMinWidget;
    private SimpleWidget m_valueMaxWidget;

    // Line Detection - Front Camera
    private SimpleWidget m_frontAreaWidget;
    private SimpleWidget m_frontAngleWidget;
    private SimpleWidget m_frontXcenterWidget;
    private SimpleWidget m_frontYcenterWidget;

    // Line Detection - Left Camera
    private SimpleWidget m_leftAreaWidget;
    private SimpleWidget m_leftAngleWidget;
    private SimpleWidget m_leftXcenterWidget;
    private SimpleWidget m_leftYcenterWidget;

    // Line Detection - Right Camera
    private SimpleWidget m_rightAreaWidget;
    private SimpleWidget m_rightAngleWidget;
    private SimpleWidget m_rightXcenterWidget;
    private SimpleWidget m_rightYcenterWidget;

    // Distance
    private SimpleWidget m_distanceWidget;

    // Constructor
    public VisionTab() {
        ShuffleLogger.logTrivial("Constructing VisionTab...");

        m_tab = Shuffleboard.getTab("Vision");
        m_hsvLayout = m_tab.getLayout("HSV Thresholds", BuiltInLayouts.kGrid);
        m_frontCamLayout = m_tab.getLayout("Front Camera", BuiltInLayouts.kGrid);
        m_leftCamLayout = m_tab.getLayout("Left Camera", BuiltInLayouts.kGrid);
        m_rightCamLayout = m_tab.getLayout("Right Camera", BuiltInLayouts.kGrid);
    }

    // Create Brain Widgets
    public void preInitialize() {
        // Hue Thresholds
        m_hueMinWidget = m_hsvLayout.add("Hue Minimum", Brain.hueMinDefault);
        Brain.hueMinEntry = m_hueMinWidget.getEntry();

        m_hueMaxWidget = m_hsvLayout.add("Hue Maximum", Brain.hueMaxDefault);
        Brain.hueMaxEntry = m_hueMaxWidget.getEntry();

        // Saturation Thresholds
        m_saturationMinWidget = m_hsvLayout.add("Saturation Minimum", Brain.saturationMinDefault);
        Brain.saturationMinEntry = m_saturationMinWidget.getEntry();

        m_saturationMaxWidget = m_hsvLayout.add("Saturation Maximum", Brain.saturationMaxDefault);
        Brain.saturationMaxEntry = m_saturationMaxWidget.getEntry();

        // Value Thresholds
        m_valueMinWidget = m_hsvLayout.add("Value Minimum", Brain.valueMinDefault);
        Brain.valueMinEntry = m_valueMinWidget.getEntry();

        m_valueMaxWidget = m_hsvLayout.add("Value Maximum", Brain.valueMaxDefault);
        Brain.valueMaxEntry = m_valueMaxWidget.getEntry();

        // Front Camera
        m_frontAreaWidget = m_frontCamLayout.add("Front Line Area", Brain.frontLineAreaDefault);
        Brain.frontLineAreaEntry = m_frontAreaWidget.getEntry();

        m_frontAngleWidget = m_frontCamLayout.add("Front Line Angle", Brain.frontLineAngleDefault);
        Brain.frontLineAngleEntry = m_frontAngleWidget.getEntry();

        m_frontXcenterWidget = m_frontCamLayout.add("Front Line Center X", Brain.frontLineXcenterDefault);
        Brain.frontLineXcenterEntry = m_frontXcenterWidget.getEntry();

        m_frontYcenterWidget = m_frontCamLayout.add("Front Line Center Y", Brain.frontLineYcenterDefault);
        Brain.frontLineYcenterEntry = m_frontYcenterWidget.getEntry();

        // Left Camera
        m_leftAreaWidget = m_leftCamLayout.add("Left Line Area", Brain.leftLineAreaDefault);
        Brain.leftLineAreaEntry = m_leftAreaWidget.getEntry();

        m_leftAngleWidget = m_leftCamLayout.add("Left Line Angle", Brain.leftLineAngleDefault);
        Brain.leftLineAngleEntry = m_leftAngleWidget.getEntry();

        m_leftXcenterWidget = m_leftCamLayout.add("Left Line Center X", Brain.leftLineXcenterDefault);
        Brain.leftLineXcenterEntry = m_leftXcenterWidget.getEntry();

        m_leftYcenterWidget = m_leftCamLayout.add("Left Line Center Y", Brain.leftLineYcenterDefault);
        Brain.leftLineYcenterEntry = m_leftYcenterWidget.getEntry();

        // Right Camera
        m_rightAreaWidget = m_rightCamLayout.add("Right Line Area", Brain.rightLineAreaDefault);
        Brain.rightLineAreaEntry = m_rightAreaWidget.getEntry();

        m_rightAngleWidget = m_rightCamLayout.add("Right Line Angle", Brain.rightLineAngleDefault);
        Brain.rightLineAngleEntry = m_rightAngleWidget.getEntry();

        m_rightXcenterWidget = m_rightCamLayout.add("Right Line Center X", Brain.rightLineXcenterDefault);
        Brain.rightLineXcenterEntry = m_rightXcenterWidget.getEntry();

        m_rightYcenterWidget = m_rightCamLayout.add("Right Line Center Y", Brain.rightLineYcenterDefault);
        Brain.rightLineYcenterEntry = m_rightYcenterWidget.getEntry();
    
        m_distanceWidget = m_tab.add("Distance Sensor", Brain.distanceDefault);
        Brain.distanceEntry = m_distanceWidget.getEntry();
    }

    // Create all other Widgets
    public void initialize() {
    }

    // Configure all Widgets
    public void configure() {
        // Layouts
        m_frontCamLayout.withPosition(0, 0);
        m_frontCamLayout.withSize(3, 2);
        m_frontCamLayout.withProperties(Map.of("Number of columns", 2));
        m_frontCamLayout.withProperties(Map.of("Number of rows", 2));
        m_frontCamLayout.withProperties(Map.of("Label position", "LEFT"));

        m_hsvLayout.withPosition(3, 0);
        m_hsvLayout.withSize(3, 2);
        m_hsvLayout.withProperties(Map.of("Number of columns", 2));
        m_hsvLayout.withProperties(Map.of("Number of rows", 2));
        m_hsvLayout.withProperties(Map.of("Label position", "LEFT"));

        m_leftCamLayout.withPosition(0, 2);
        m_leftCamLayout.withSize(3, 2);
        m_leftCamLayout.withProperties(Map.of("Number of columns", 2));
        m_leftCamLayout.withProperties(Map.of("Number of rows", 2));
        m_leftCamLayout.withProperties(Map.of("Label position", "LEFT"));

        m_rightCamLayout.withPosition(3, 2);
        m_rightCamLayout.withSize(3, 2);
        m_rightCamLayout.withProperties(Map.of("Number of columns", 2));
        m_rightCamLayout.withProperties(Map.of("Number of rows", 2));
        m_rightCamLayout.withProperties(Map.of("Label position", "LEFT"));

        // HSV Thresholds
        m_hueMinWidget.withWidget(BuiltInWidgets.kTextView);
        m_hueMaxWidget.withWidget(BuiltInWidgets.kTextView);
        m_saturationMinWidget.withWidget(BuiltInWidgets.kTextView);
        m_saturationMaxWidget.withWidget(BuiltInWidgets.kTextView);
        m_valueMinWidget.withWidget(BuiltInWidgets.kTextView);
        m_valueMaxWidget.withWidget(BuiltInWidgets.kTextView);

        // Front Camera
        m_frontAreaWidget.withWidget(BuiltInWidgets.kTextView);
        m_frontAngleWidget.withWidget(BuiltInWidgets.kTextView);
        m_frontXcenterWidget.withWidget(BuiltInWidgets.kTextView);
        m_frontYcenterWidget.withWidget(BuiltInWidgets.kTextView);

        // Left Camera
        m_leftAreaWidget.withWidget(BuiltInWidgets.kTextView);
        m_leftAngleWidget.withWidget(BuiltInWidgets.kTextView);
        m_leftXcenterWidget.withWidget(BuiltInWidgets.kTextView);
        m_leftYcenterWidget.withWidget(BuiltInWidgets.kTextView);

        // Right Camera
        m_rightAreaWidget.withWidget(BuiltInWidgets.kTextView);
        m_rightAngleWidget.withWidget(BuiltInWidgets.kTextView);
        m_rightXcenterWidget.withWidget(BuiltInWidgets.kTextView);
        m_rightYcenterWidget.withWidget(BuiltInWidgets.kTextView);
        
        // Distance
        m_distanceWidget.withWidget(BuiltInWidgets.kTextView);
        m_distanceWidget.withPosition(5, 5); 
    }

    // This will be called in the robotPeriodic
    public void update() {
    }

}
