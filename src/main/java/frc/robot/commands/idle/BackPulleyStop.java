
package frc.robot.commands.idle;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command stops the BackPulley motor
public class BackPulleyStop extends Command {

    public BackPulleyStop() {
        Logger.setup("Constructing Command: BackPulleyStop...");

        // Declare subsystem dependencies
        requires(Robot.robotBackPulley);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: BackPulleyStop...");
    }

    @Override
    protected void execute() {
        Robot.robotBackPulley.stop();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: BackPulleyStop...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: BackPulleyStop...");
    }

}
