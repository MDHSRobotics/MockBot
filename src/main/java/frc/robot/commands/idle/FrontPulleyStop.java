
package frc.robot.commands.idle;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command stops the FrontPulley motor
public class FrontPulleyStop extends Command {

    public FrontPulleyStop() {
        Logger.setup("Constructing Command: FrontPulleyStop...");

        // Declare subsystem dependencies
        requires(Robot.robotLiftPulley);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: FrontPulleyStop...");
    }

    @Override
    protected void execute() {
        Robot.robotLiftPulley.stop();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: FrontPulleyStop...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: FrontPulleyStop...");
    }

}
