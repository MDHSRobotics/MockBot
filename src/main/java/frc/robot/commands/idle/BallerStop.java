
package frc.robot.commands.idle;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command stops the Baller motor
public class BallerStop extends Command {

    public BallerStop() {
        Logger.setup("Constructing Command: BallerStop...");

        // Declare subsystem dependencies
        requires(Robot.robotBaller);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: BallerStop...");
    }

    @Override
    protected void execute() {
        Robot.robotBaller.stop();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: BallerStop...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: BallerStop...");
    }

}
