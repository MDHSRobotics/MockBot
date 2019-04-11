package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// Resets Ball position
public class BallReset extends Command {

    public BallReset() {
        Logger.setup("Constructing Command: BallReset...");

        // Declare subsystem dependencies
        requires(Robot.robotBaller);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: BallReset...");
    }

    @Override
    protected void execute() {
        Robot.robotBaller.resetPosition();
    }

    // This command finishes immediately, but is intended to be continually restarted while a button is held
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: BallReset...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: BallReset...");

        Robot.robotBaller.stop();
    }

}
