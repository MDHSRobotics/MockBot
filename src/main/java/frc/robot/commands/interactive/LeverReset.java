package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// Resets Lever position
public class LeverReset extends Command {

    public LeverReset() {
        Logger.setup("Constructing Command: LeverReset...");

        // Declare subsystem dependencies
        requires(Robot.robotLever);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: LeverReset...");
    }

    @Override
    protected void execute() {
        Robot.robotLever.resetPosition();
    }

    // This command finishes immediately, but is intended to be continually restarted while a button is held
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: LeverReset...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: LeverReset...");

        Robot.robotLever.stop();
    }

}
