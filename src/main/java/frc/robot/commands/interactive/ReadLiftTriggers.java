
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.helpers.CartesianMovement;
import frc.robot.OI;
import frc.robot.Robot;


// This command uses the joystick input to mecanum drive using the cartesian method
public class ReadLiftTriggers extends Command {

    public ReadLiftTriggers() {
        Logger.setup("Constructing Command: ReadLiftTriggers...");

        // Declare subsystem dependencies
        requires(Robot.robotJuanLift);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: ReadLiftTriggers...");
    }

    @Override
    protected void execute() {
        double move = OI.getLiftSpeed();
        Robot.robotJuanLift.getTriggerValue(move);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: ReadLiftTriggers...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: ReadLiftTriggers...");
    }

}
