
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.OI;
import frc.robot.Robot;


// Automatically control the MecDrive to align the Robot with the gyro, and the line seen by the vision system
public class MecDriveAlign extends Command {

    private int m_targetAngle = -1;

    public MecDriveAlign() {
        Logger.setup("Constructing Command: MecDriveAlign...");

        // Declare subsystem dependencies
        requires(Robot.robotMecDriver);
    }

    @Override
    protected void initialize() {
        System.out.println("--");
        Logger.action("Initializing Command: MecDriveAlign...");

        m_targetAngle = OI.getDpadAngleForGyro();
    }

    @Override
    protected void execute() {
        if (m_targetAngle != -1) {
            Robot.robotMecDriver.driveAlign(m_targetAngle);
        }
    }

    // This finishes immediately, but is intended to be continually restarted while a button is held
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDriveAlign...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: MecDriveAlign...");
    }

}
