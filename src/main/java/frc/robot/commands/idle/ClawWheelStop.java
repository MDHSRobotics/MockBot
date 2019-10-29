
package frc.robot.commands.idle;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command stops the Hatcher motor
public class ClawWheelStop extends Command {

    public void ClawWheelStop() {
        Logger.setup("Constructing Command: HatcherStop...");

        // Declare subsystem dependencies
        requires(Robot.robotClawWheel);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: HatcherStop...");
    }

    @Override
    protected void execute() {
        Robot.robotClawWheel.stop();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: HatcherStop...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: HatcherStop...");
    }

}
