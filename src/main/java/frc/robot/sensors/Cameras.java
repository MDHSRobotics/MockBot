
package frc.robot.sensors;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import java.util.concurrent.TimeUnit;

import frc.robot.consoles.Logger;
// import frc.robot.Brain;


public class Cameras {

    public static final int CAM_RESOLUTION_WIDTH = 320;
    public static final int CAM_RESOLUTION_HEIGHT = 240;
    public static final int FPS = 30;

    private static final long SLEEP_SECONDS = 1;

    public static UsbCamera captureCamera(int deviceNumber) {
        Logger.action("Starting Camera Capture... Device: " + deviceNumber);

        CameraServer camServer = CameraServer.getInstance();
        UsbCamera cam = camServer.startAutomaticCapture(deviceNumber);
        cam.setResolution(CAM_RESOLUTION_WIDTH, CAM_RESOLUTION_HEIGHT);
        cam.setFPS(FPS);

        // int brightness = (int)Brain.getBrightness();
        // int exposure = (int)Brain.getExposure();
        // int whiteBalance = (int)Brain.getWhiteBalance();

        // cam.setBrightness(brightness);
        // cam.setExposureManual(exposure);
        // cam.setWhiteBalanceManual(whiteBalance);

        return cam;
    }

    public static boolean testConnection(int deviceNumber) {
        Logger.action("Checking for USB Camera Connection... Device: " + deviceNumber);

        boolean cameraIsConnected = false;
        UsbCamera testCam = new UsbCamera("Test USB Camera " + deviceNumber, deviceNumber);
        try {
            Logger.waiting("Waiting for Test USB Camera " + deviceNumber + " to connect...");
            try {
                TimeUnit.SECONDS.sleep(SLEEP_SECONDS);
            }
            catch (InterruptedException e) {
                Logger.warning("Swallowed InterruptedException: " + e);
            }
            cameraIsConnected = testCam.isConnected();
        }
        finally {
            testCam.close();
        }
        if (!cameraIsConnected) {
            Logger.error("USB Camera " + deviceNumber + " not found, disabled!");
        }
        return cameraIsConnected;
    }

}
