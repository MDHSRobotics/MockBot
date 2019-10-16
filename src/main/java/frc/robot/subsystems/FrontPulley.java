
// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.command.Subsystem;

// import frc.robot.commands.idle.FrontPulleyStop;
// import frc.robot.consoles.Logger;
// import frc.robot.Devices;


// // Pulley subsystem for lifting the front end of robot up above a platform
// public class FrontPulley extends Subsystem {

//     // Motor constants
//     private final double SECONDS_FROM_NEUTRAL_TO_FULL = 0;
//     private final int TIMEOUT_MS = 10;

//     // The Talon connection state, to prevent watchdog warnings during testing
//     private boolean m_talonsAreConnected = false;

//     public FrontPulley() {
//         Logger.setup("Constructing Subsystem: FrontPulley...");

//         boolean talonAIsConnected = Devices.isConnected(Devices.talonSrxFrontPulleyA);
//         boolean talonBIsConnected = Devices.isConnected(Devices.talonSrxFrontPulleyB);
//         boolean talonCIsConnected = Devices.isConnected(Devices.talonSrxFrontPulleyC);
//         m_talonsAreConnected = (talonAIsConnected &&
//                                 talonBIsConnected && 
//                                 talonCIsConnected);

//         if (!m_talonsAreConnected) {
//             Logger.error("Pulley talons not all connected! Disabling FrontPulley...");
//         }
//         else {
//             Devices.talonSrxFrontPulleyA.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
//             Devices.talonSrxFrontPulleyB.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
//             Devices.talonSrxFrontPulleyC.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
//         }
//     }

//     @Override
//     public void initDefaultCommand() {
//         Logger.setup("Initializing Pulley DefaultCommand -> FrontPulleyStop...");

//         setDefaultCommand(new FrontPulleyStop());
//     }

//     // Stop the Pulley motor
//     public void stop() {
//         if (!m_talonsAreConnected) return;
//         Devices.talonSrxFrontPulleyA.stopMotor();
//         Devices.talonSrxFrontPulleyB.stopMotor();
//         Devices.talonSrxFrontPulleyC.stopMotor();
//     }

//     // Set the Pulley motor speed explicitly
//     public void setSpeed(double speed) {
//         if (!m_talonsAreConnected) return;
//         Devices.talonSrxFrontPulleyA.set(speed);
//         Devices.talonSrxFrontPulleyB.set(speed);
//         Devices.talonSrxFrontPulleyC.set(speed);
//     }

//     //---------//
//     // Testing //
//     //---------//

//     public void testMotors() {
//         if (!m_talonsAreConnected) return;
//         Devices.talonSrxFrontPulleyA.set(0.2);
//     }

// }
