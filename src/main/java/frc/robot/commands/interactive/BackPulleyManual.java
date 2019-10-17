
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.OI;
import frc.robot.Robot;


// This command lifts the BackPulley manually
public class BackPulleyManual extends Command {

    public BackPulleyManual() {
        Logger.setup("Constructing Command: BackPulleyManual...");

        requires(Robot.robotLiftPulley);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: BackPulleyManual...");
    }

    @Override
    protected void execute() {
        double speed = OI.getBackPulleyLiftSpeed();
        Robot.robotLiftPulley.setSpeed(speed);
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
