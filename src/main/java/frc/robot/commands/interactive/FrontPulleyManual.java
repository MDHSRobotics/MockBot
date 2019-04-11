
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.OI;
import frc.robot.Robot;


// This command lifts the FrontPulley manually
public class FrontPulleyManual extends Command {

    public FrontPulleyManual() {
        Logger.setup("Constructing Command: FrontPulleyManual...");

        requires(Robot.robotFrontPulley);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: FrontPulleyManual...");
    }

    @Override
    protected void execute() {
        double speed = OI.getFrontPulleyLiftSpeed();
        Robot.robotFrontPulley.setSpeed(speed);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: FrontPulleyManual...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: FrontPulleyManual...");
    }

}
