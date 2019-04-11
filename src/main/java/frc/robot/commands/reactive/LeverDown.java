
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command moves the Leverer to hold the cargo ball via encoder, and keeps it there until it is ready to be tossed
public class LeverDown extends Command {

    public LeverDown() {
        Logger.setup("Constructing Command: LeverDown...");

        // Declare subsystem dependencies
        requires(Robot.robotLever);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: LeverDown...");

        // Set encoded position
        Robot.robotLever.downLever();
    }

    @Override
    protected void execute() {
        int position = Robot.robotLever.getPosition();
        int velocity = Robot.robotLever.getVelocity();
        Logger.info("LeverDown -> Position: " + position + "; Velocity: " + velocity);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: LeverDown...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: LeverDown...");
    }

}
