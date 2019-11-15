
package frc.robot.helpers;

import java.lang.Math;
// TODO change name to "TalonUtils"
public class TalonUtils {
    
    //number of miliseconds that the talon can stay at peak current
    public static final int PEAK_AMPERAGE_DURATION = 40;

    //max amps that the talon can supply at peak
    public static final int PEAK_AMPERAGE = 11;

    //max amps that the talon can supply continuously
    public static final int CONTINUOUS_AMPERAGE_LIMIT = 10;
    
    //minimum speed the talon can move forwards
    public static final int NOMINAL_OUTPUT_FORWARD = 0;

    //minimum speed the talon can move backwards
    public static final int NOMINAL_OUTPUT_REVERSE = 0;
    
    /*
     * Set to zero to skip waiting for confirmation, set to nonzero to wait and
     * report to DS if action fails.
     */
    public static final int TIMEOUT_MS = 20;


    //------------------------------encoder constants----------------------------------------//
    /**
     * Which PID slot to pull gains from. Starting 2018, you can choose from
     * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
     * configuration.
     */
    public static final int PID_SLOT_0 = 0;

    /*
     * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
     * now we just want the primary one.
     */
    public static final int PID_LOOP_PRIMARY = 0;

    // The amount of native ticks per revolution (Tpr) in the Redline Encoder
    public static final int ENCODER_TPR = 4096;

    // Computes the encoder ticks based on the desired rotation in degrees for a given gearbox ratio
    public static double translateAngleToTicks(double angle, double gearRatio){
        double rotationCountGS = angle / 360; // Amount of rotations on the gearbox shaft
        double rotationCountMS = rotationCountGS * gearRatio; // Amount of rotations on the motor shaft
        double rotationTicks = rotationCountMS * TalonUtils.ENCODER_TPR; // Amount of ticks to rotate
        return rotationTicks;
    }

    // Computes the encoder ticks based on the desired distance in inches for a given winch diameter and gearbox ratio
    public static double translateDistanceToTicks(double distance, double spoolDiameter, double gearRatio){
        double spoolCircumerence = Math.PI * spoolDiameter;
        double rotationCountGS = distance / spoolCircumerence; // Amount of rotations on the gearbox shaft
        double rotationCountMS = rotationCountGS * gearRatio; // Amount of rotations on the motor shaft
        double rotationTicks = rotationCountMS * TalonUtils.ENCODER_TPR; // Amount of ticks to rotate
        return rotationTicks;
    }

    public static double translateRotationsToTicks(double rotations, double gearRatio){
        double rotationCountGS = rotations;
        double rotationCountMS = rotationCountGS / gearRatio;
        double rotationTicks = rotationCountMS * TalonUtils.ENCODER_TPR;
        return rotationTicks;
    }

}
