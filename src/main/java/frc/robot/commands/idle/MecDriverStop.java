
package frc.robot.commands.idle;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command stops the MecDriver
public class MecDriverStop extends Command {

    public MecDriverStop() {
        Logger.setup("Constructing Command: MecDriverStop...");

        // Declare subsystem dependencies
        requires(Robot.robotMecDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MecDriverStop...");
    }

    @Override
    protected void execute() {
        Robot.robotMecDriver.stop();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDriverStop...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: MecDriverStop...");
    }

}
