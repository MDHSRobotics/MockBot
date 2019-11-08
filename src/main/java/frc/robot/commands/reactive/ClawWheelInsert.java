
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;



// This command closes the Hatcher claw via encoder to release, or in preparating to grab, the hatch, and keeps it closed
public class ClawWheelInsert extends Command {

    public ClawWheelInsert() {
        Logger.setup("Constructing Command: ClawWheelInsert...");

        // Declare subsystem dependencies
        requires(Robot.robotClawWheel);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: ClawWheelInsert...");

        // Set encoded position
        Robot.robotClawWheel.stop();
    }

    @Override
    protected void execute() {
        Robot.robotClawWheel.insertBox();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: HatchClawClose...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: HatchClawClose...");
    }

}
