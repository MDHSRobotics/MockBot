
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.OI;
import frc.robot.Robot;


// This command lifts the LiftPulley manually
public class LiftPulleyRaise extends Command {

    public LiftPulleyRaise() {
        Logger.setup("Constructing Command: LiftPulleyManual...");

        requires(Robot.robotLiftPulley);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: LiftPulleyManual...");
    }

    @Override
    protected void execute() {
        Robot.robotLiftPulley.raise();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: LiftPulleyManual...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: LiftPulleyManual...");
    }

}
