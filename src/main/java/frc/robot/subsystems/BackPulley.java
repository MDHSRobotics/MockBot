
// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.command.Subsystem;

// import frc.robot.commands.idle.BackPulleyStop;
// import frc.robot.consoles.Logger;
// import frc.robot.Devices;


// // Pulley subsystem for lifting the back end of robot up above a platform
// public class BackPulley extends Subsystem {

//     // Motor constants
//     private final double SECONDS_FROM_NEUTRAL_TO_FULL = 0;
//     private final int TIMEOUT_MS = 10;

//     // The Talon connection state, to prevent watchdog warnings during testing
//     private boolean m_talonsAreConnected = false;

//     public BackPulley() {
//         Logger.setup("Constructing Subsystem: BackPulley...");

//         boolean talonAIsConnected = Devices.isConnected(Devices.talonSrxBackPulleyA);
//         boolean talonBIsConnected = Devices.isConnected(Devices.talonSrxBackPulleyB);
//         boolean talonCIsConnected = Devices.isConnected(Devices.talonSrxBackPulleyC);
//         m_talonsAreConnected = (talonAIsConnected &&
//                                 talonBIsConnected && 
//                                 talonCIsConnected);

//         if (!m_talonsAreConnected) {
//             Logger.error("Pulley talons not all connected! Disabling BackPulley...");
//         }
//         else {
//             Devices.talonSrxBackPulleyA.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
//             Devices.talonSrxBackPulleyB.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
//             Devices.talonSrxBackPulleyC.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
//         }
//     }

//     @Override
//     public void initDefaultCommand() {
//         Logger.setup("Initializing Pulley DefaultCommand -> BackPulleyStop...");

//         setDefaultCommand(new BackPulleyStop());
//     }

//     // Stop the Pulley motor
//     public void stop() {
//         if (!m_talonsAreConnected) return;
//         Devices.talonSrxBackPulleyA.stopMotor();
//         Devices.talonSrxBackPulleyB.stopMotor();
//         Devices.talonSrxBackPulleyC.stopMotor();
//     }

//     // Set the Pulley motor speed explicitly
//     public void setSpeed(double speed) {
//         if (!m_talonsAreConnected) return;
//         Devices.talonSrxBackPulleyA.set(speed);
//         Devices.talonSrxBackPulleyB.set(speed);
//         Devices.talonSrxBackPulleyC.set(speed);
//     }

//     //---------//
//     // Testing //
//     //---------//

//     public void testMotors() {
//         if (!m_talonsAreConnected) return;
//         Devices.talonSrxBackPulleyA.set(0.2);
//     }

// }
