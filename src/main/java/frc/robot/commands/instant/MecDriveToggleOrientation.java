
package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command stops the mecanum drive, and toggles the control orientation
public class MecDriveToggleOrientation extends Command {

    public MecDriveToggleOrientation() {
        Logger.setup("Constructing Command: MecDriveToggleOrientation...");

        // Declare subsystem dependencies
        requires(Robot.robotMecDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MecDriveToggleOrientation...");

        Robot.robotMecDriver.stop();
    }

    @Override
    protected void execute() {
        Robot.robotMecDriver.toggleDriveOrientation();
    }

    // This command finishes immediately
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDriveToggleOrientation...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: MecDriveToggleOrientation...");
    }

}
