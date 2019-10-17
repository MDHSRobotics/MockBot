
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.OI;
import frc.robot.Robot;


// This command lifts the LiftPulley manually
public class LiftPulleyManual extends Command {

    public LiftPulleyManual() {
        Logger.setup("Constructing Command: LiftPulleyManual...");

        requires(Robot.robotLiftPulley);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: LiftPulleyManual...");
    }

    @Override
    protected void execute() {
        double speed = OI.getLiftPulleyLiftSpeed();
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
