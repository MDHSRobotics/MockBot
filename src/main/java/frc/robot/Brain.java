
package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.subsystems.MecDriver.DriveOrientation;
import frc.robot.OI.ControlStick;


// This class contains all the shared NetworkTableEntries for the Robot,
// their default values, and methods for retrieving their current values
public class Brain {

    //----------------//
    // Default Values //
    //----------------//

    // Shuffler - Main Tab
    public static double matchTimeDefault = 0;

    // Shuffler - Drive Tab
    public static double driveTargetDistanceDefault = 2.0;

    // OI
    public static ControlStick controlStickDefault = ControlStick.XBOX;

    // OI - Joystick
    public static double yDeadZoneDefault = .1;
    public static double xDeadZoneDefault = .1;
    public static double zDeadZoneDefault = .5;
    public static double ySensitivityDefault = .5;
    public static double xSensitivityDefault = .5;
    public static double zSensitivityDefault = .5;

    // OI - Xbox Thumbsticks
    public static double yLeftDeadZoneDefault = .001;
    public static double xLeftDeadZoneDefault = .001;
    public static double yRightDeadZoneDefault = .001;
    public static double xRightDeadZoneDefault = .001;
    public static double yLeftSensitivityDefault = .75;
    public static double xLeftSensitivityDefault = .75;
    public static double yRightSensitivityDefault = .5;
    public static double xRightSensitivityDefault = .5;

    // Sight
    public static double brightnessDefault = 40;
    public static double exposureDefault = 30;
    public static double whiteBalanceDefault = 4500;

    // Vision - Line Pipeline
    public static double hueMinDefault = 0;
    public static double hueMaxDefault = 180;
    public static double saturationMinDefault = 0;
    public static double saturationMaxDefault = 170;
    public static double valueMinDefault = 115;
    public static double valueMaxDefault = 255;

    // Vision - Front Line
    public static double frontLineAreaDefault = 0;
    public static double frontLineAngleDefault = 0;
    public static double frontLineXcenterDefault = 0;
    public static double frontLineYcenterDefault = 0;
    public static boolean frontLineDetectedDefault = false;

    // Vision - Left Line
    public static double leftLineAreaDefault = 0;
    public static double leftLineAngleDefault = 0;
    public static double leftLineXcenterDefault = 0;
    public static double leftLineYcenterDefault = 0;
    public static boolean leftLineDetectedDefault = false;

    // Vision - Right Line
    public static double rightLineAreaDefault = 0;
    public static double rightLineAngleDefault = 0;
    public static double rightLineXcenterDefault = 0;
    public static double rightLineYcenterDefault = 0;
    public static boolean rightLineDetectedDefault = false;

    // Distance
    public static double distanceDefault = -1;

    // Subsystem - MecDriver
    public static DriveOrientation driveOrientationDefault = DriveOrientation.ROBOT;
    public static double alignFrontMagnitudeDefault = .5;
    public static double alignSideMagnitudeDefault = .5;
    public static double alignZSensitivityDefault = .7;
    public static double alignZSpeedMinimumDefault = .25;
    public static double alignZToleranceDefault = 10;

    // Subsystem - Baller
    public static double ballTossAngleDefault = 40;

    // Subsystem - Hatcher
    public static double hatchOpenAngleDefault = 45;
    public static double hatchCloseAngleDefault = 75;

    // Subsystem - Arm
    public static double armHAB2AngleDefault = 55;
    public static double armHAB3AngleDefault = 110;
    public static double armFullAngleDefault = 220;

    // Subsystem - Pulley
    public static double pulleyHAB2DistanceDefault = 120;
    public static double pulleyHAB3DistanceDefault = 240;

    // Subsystem - Lift Pulley
    public static double liftPulleyDistanceDefault = 100; // TODO: Find real distance

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    // Shuffler - Main Tab
    public static NetworkTableEntry matchTimeEntry;

    // Shuffler - Drive Tab
    public static NetworkTableEntry driveTargetDistanceEntry;

    // OI
    public static NetworkTableEntry controlStickEntry;

    // OI - Joystick
    public static NetworkTableEntry yDeadZoneEntry;
    public static NetworkTableEntry xDeadZoneEntry;
    public static NetworkTableEntry zDeadZoneEntry;
    public static NetworkTableEntry ySensitivityEntry;
    public static NetworkTableEntry xSensitivityEntry;
    public static NetworkTableEntry zSensitivityEntry;

    // OI - Xbox Thumbsticks
    public static NetworkTableEntry yLeftDeadZoneEntry;
    public static NetworkTableEntry xLeftDeadZoneEntry;
    public static NetworkTableEntry yRightDeadZoneEntry;
    public static NetworkTableEntry xRightDeadZoneEntry;
    public static NetworkTableEntry yLeftSensitivityEntry;
    public static NetworkTableEntry xLeftSensitivityEntry;
    public static NetworkTableEntry yRightSensitivityEntry;
    public static NetworkTableEntry xRightSensitivityEntry;

    // Sight
    public static NetworkTableEntry brightnessEntry;
    public static NetworkTableEntry exposureEntry;
    public static NetworkTableEntry whiteBalanceEntry;

    // Vision - Line Pipeline
    public static NetworkTableEntry hueMinEntry;
    public static NetworkTableEntry hueMaxEntry;
    public static NetworkTableEntry saturationMinEntry;
    public static NetworkTableEntry saturationMaxEntry;
    public static NetworkTableEntry valueMinEntry;
    public static NetworkTableEntry valueMaxEntry;

    // Vision - Front Line
    public static NetworkTableEntry frontLineAreaEntry;
    public static NetworkTableEntry frontLineAngleEntry;
    public static NetworkTableEntry frontLineXcenterEntry;
    public static NetworkTableEntry frontLineYcenterEntry;
    public static NetworkTableEntry frontLineDetectedEntry;

    // Vision - Left Line
    public static NetworkTableEntry leftLineAreaEntry;
    public static NetworkTableEntry leftLineAngleEntry;
    public static NetworkTableEntry leftLineXcenterEntry;
    public static NetworkTableEntry leftLineYcenterEntry;
    public static NetworkTableEntry leftLineDetectedEntry;

    // Vision - Right Line
    public static NetworkTableEntry rightLineAreaEntry;
    public static NetworkTableEntry rightLineAngleEntry;
    public static NetworkTableEntry rightLineXcenterEntry;
    public static NetworkTableEntry rightLineYcenterEntry;
    public static NetworkTableEntry rightLineDetectedEntry;
    
    // Distance 
    public static NetworkTableEntry distanceEntry;

    // Subsystem - MecDriver 
    public static NetworkTableEntry driveOrientationEntry;
    public static NetworkTableEntry alignFrontMagnitudeEntry;
    public static NetworkTableEntry alignSideMagnitudeEntry;
    public static NetworkTableEntry alignZSensitivityEntry;
    public static NetworkTableEntry alignZSpeedMinimumEntry;
    public static NetworkTableEntry alignZToleranceEntry;

    // Subsystem - Baller
    public static NetworkTableEntry ballTossAngleEntry;

    // Subsystem - Hatcher
    public static NetworkTableEntry hatchOpenAngleEntry;
    public static NetworkTableEntry hatchCloseAngleEntry;

    // Subsystem - Arm
    public static NetworkTableEntry armHAB2AngleEntry;
    public static NetworkTableEntry armHAB3AngleEntry;
    public static NetworkTableEntry armFullAngleEntry;

    // Subsystem - Pulley
    public static NetworkTableEntry pulleyHAB2DistanceEntry;
    public static NetworkTableEntry pulleyHAB3DistanceEntry;

    // Subsystem - Lift Pulley
    public static NetworkTableEntry liftPulleyDistanceEntry;

    //---------//
    // Setters //
    //---------//

    // Shuffler - Main Tab
    public static void setMatchTime() {
        DriverStation ds = DriverStation.getInstance();
        double matchTime = ds.getMatchTime();
        matchTimeEntry.setDouble(matchTime);
    }

    // OI
    public static void setControlStick(ControlStick stick) {
        String value = stick.toString();
        controlStickEntry.setValue(value);
    }

    // Vision - Front Line

    public static void setFrontLineArea(double value) {
        frontLineAreaEntry.setDouble(value);
    }

    public static void setFrontLineAngle(double value) {
        frontLineAngleEntry.setDouble(value);
    }

    public static void setFrontLineXcenter(double value) {
        frontLineXcenterEntry.setDouble(value);
    }

    public static void setFrontLineYcenter(double value) {
        frontLineYcenterEntry.setDouble(value);
    }

    // Vision - Left Line
    public static void setLeftLineArea(double value) {
        leftLineAreaEntry.setDouble(value);
    }

    public static void setLeftLineAngle(double value) {
        leftLineAngleEntry.setDouble(value);
    }

    public static void setLeftLineXcenter(double value) {
        leftLineXcenterEntry.setDouble(value);
    }

    public static void setLeftLineYcenter(double value) {
        leftLineYcenterEntry.setDouble(value);
    }

    // Vision - Right Line
    public static void setRightLineArea(double value) {
        rightLineAreaEntry.setDouble(value);
    }

    public static void setRightLineAngle(double value) {
        rightLineAngleEntry.setDouble(value);
    }

    public static void setRightLineXcenter(double value) {
        rightLineXcenterEntry.setDouble(value);
    }

    public static void setRightLineYcenter(double value) {
        rightLineYcenterEntry.setDouble(value);
    }

    // Subsystems - MecDriver
    public static void setDriveOrientation(DriveOrientation orientation) {
        String value = orientation.toString();
        driveOrientationEntry.setValue(value);
    }

    //---------//
    // Getters //
    //---------//

    // Shuffler - Main Tab
    public static double getMatchTime() {
        return matchTimeEntry.getDouble(matchTimeDefault);
    }

    // Shuffler - Drive Tab
    public static double getTargetDriveDistance() {
        return driveTargetDistanceEntry.getDouble(driveTargetDistanceDefault);
    }

    // OI
    public static ControlStick getControlStick() {
        String defaultString = controlStickDefault.toString();
        String stickString = controlStickEntry.getString(defaultString);
        return ControlStick.valueOf(stickString);
    }

    // OI - Joystick
    public static double getYdeadZone() {
        return yDeadZoneEntry.getDouble(yDeadZoneDefault);
    }

    public static double getXdeadZone() {
        return xDeadZoneEntry.getDouble(xDeadZoneDefault);
    }

    public static double getZdeadZone() {
        return zDeadZoneEntry.getDouble(zDeadZoneDefault);
    }

    public static double getYsensitivity() {
        return ySensitivityEntry.getDouble(ySensitivityDefault);
    }

    public static double getXsensitivity() {
        return xSensitivityEntry.getDouble(xSensitivityDefault);
    }

    public static double getZsensitivity() {
        return zSensitivityEntry.getDouble(zSensitivityDefault);
    }

    // OI - Xbox Thumbsticks
    public static double getYleftDeadZone() {
        return yLeftDeadZoneEntry.getDouble(yLeftDeadZoneDefault);
    }

    public static double getXleftDeadZone() {
        return xLeftDeadZoneEntry.getDouble(xLeftDeadZoneDefault);
    }

    public static double getYrightDeadZone() {
        return yRightDeadZoneEntry.getDouble(yRightDeadZoneDefault);
    }

    public static double getXrightDeadZone() {
        return xRightDeadZoneEntry.getDouble(xRightDeadZoneDefault);
    }

    public static double getYleftSensitivity() {
        return yLeftSensitivityEntry.getDouble(yLeftSensitivityDefault);
    }

    public static double getXleftSensitivity() {
        return xLeftSensitivityEntry.getDouble(xLeftSensitivityDefault);
    }

    public static double getYrightSensitivity() {
        return yRightSensitivityEntry.getDouble(yRightSensitivityDefault);
    }

    public static double getXrightSensitivity() {
        return xRightSensitivityEntry.getDouble(xRightSensitivityDefault);
    }

    // Sight
    public static double getBrightness() {
        return brightnessEntry.getDouble(brightnessDefault);
    }

    public static double getExposure() {
        return exposureEntry.getDouble(exposureDefault);
    }

    public static double getWhiteBalance() {
        return whiteBalanceEntry.getDouble(whiteBalanceDefault);
    }

    // Vision - Line Pipeline
    public static double getHueMin() {
        return hueMinEntry.getDouble(hueMinDefault);
    }

    public static double getHueMax() {
        return hueMaxEntry.getDouble(hueMaxDefault);
    }

    public static double getSaturationMin() {
        return saturationMinEntry.getDouble(saturationMinDefault);
    }

    public static double getSaturationMax() {
        return saturationMaxEntry.getDouble(saturationMaxDefault);
    }

    public static double getValueMin() {
        return valueMinEntry.getDouble(valueMinDefault);
    }

    public static double getValueMax() {
        return valueMaxEntry.getDouble(valueMaxDefault);
    }

    // Vision - Front Line
    public static double getFrontLineArea() {
        return frontLineAreaEntry.getDouble(frontLineAreaDefault);
    }

    public static double getFrontLineAngle() {
        return frontLineAngleEntry.getDouble(frontLineAngleDefault);
    }

    public static double getFrontLineXcenter() {
        return frontLineXcenterEntry.getDouble(frontLineXcenterDefault);
    }

    public static double getFrontLineYcenter() {
        return frontLineYcenterEntry.getDouble(frontLineYcenterDefault);
    }

    // Vision - Left Line
    public static double getLeftLineArea() {
        return leftLineAreaEntry.getDouble(leftLineAreaDefault);
    }

    public static double getLeftLineAngle() {
        return leftLineAngleEntry.getDouble(leftLineAngleDefault);
    }

    public static double getLeftLineXcenter() {
        return leftLineXcenterEntry.getDouble(leftLineXcenterDefault);
    }

    public static double getLeftLineYcenter() {
        return leftLineYcenterEntry.getDouble(leftLineYcenterDefault);
    }

    // Vision - Right Line
    public static double getRightLineArea() {
        return rightLineAreaEntry.getDouble(rightLineAreaDefault);
    }

    public static double getRightLineAngle() {
        return rightLineAngleEntry.getDouble(rightLineAngleDefault);
    }

    public static double getRightLineXcenter() {
        return rightLineXcenterEntry.getDouble(rightLineXcenterDefault);
    }

    public static double getRightLineYcenter() {
        return rightLineYcenterEntry.getDouble(rightLineYcenterDefault);
    }

    // Distance 
    public static double getDistance() {
        return distanceEntry.getDouble(distanceDefault);
    }

    // Subsystems - MecDriver
    public static DriveOrientation getDriveOrientation() {
        String defaultString = driveOrientationDefault.toString();
        String orientationString = driveOrientationEntry.getString(defaultString);
        return DriveOrientation.valueOf(orientationString);
    }

    public static double getAlignFrontMagnitude() {
        return alignFrontMagnitudeEntry.getDouble(alignFrontMagnitudeDefault);
    }

    public static double getAlignSideMagnitude() {
        return alignSideMagnitudeEntry.getDouble(alignSideMagnitudeDefault);
    }

    public static double getAlignZSensitivity() {
        return alignZSensitivityEntry.getDouble(alignZSensitivityDefault);
    }

    public static double getAlignZSpeedMinimum() {
        return alignZSpeedMinimumEntry.getDouble(alignZSpeedMinimumDefault);
    }

    public static double getAlignZTolerance() {
        return alignZToleranceEntry.getDouble(alignZToleranceDefault);
    }

    // Subsystems - Baller
    public static double getBallTossAngle() {
        return ballTossAngleEntry.getDouble(ballTossAngleDefault);
    }

    // Subsystems - Hatcher
    public static double getHatchOpenAngle() {
        return hatchOpenAngleEntry.getDouble(hatchOpenAngleDefault);
    }

    public static double getHatchCloseAngle() {
        return hatchCloseAngleEntry.getDouble(hatchCloseAngleDefault);
    }

    // Subystems - Arm
    public static double getArmHAB2Angle() {
        return armHAB2AngleEntry.getDouble(armHAB2AngleDefault);
    }

    public static double getArmHAB3Angle() {
        return armHAB3AngleEntry.getDouble(armHAB3AngleDefault);
    }

    public static double getArmFullAngle() {
        return armFullAngleEntry.getDouble(armFullAngleDefault);
    }

    // Subystems - Pulley
    public static double getPulleyHAB2Distance() {
        return pulleyHAB2DistanceEntry.getDouble(pulleyHAB2DistanceDefault);
    }

    public static double getPulleyHAB3Distance() {
        return pulleyHAB3DistanceEntry.getDouble(pulleyHAB3DistanceDefault);
    }

    public static double getLiftPulleyDistance() {
        return liftPulleyDistanceEntry.getDouble(liftPulleyDistanceDefault);
    }
}
