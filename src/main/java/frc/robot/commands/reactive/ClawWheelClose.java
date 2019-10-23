
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command closes the Hatcher claw via encoder to release, or in preparating to grab, the hatch, and keeps it closed
public class ClawWheelClose extends Command {

    public ClawWheelClose() {
        Logger.setup("Constructing Command: ClawWheelClose...");

        // Declare subsystem dependencies
        requires(Robot.robotClawWheel);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: ClawWheelClose...");
        Robot.robotClawWheel.closeWheel();
    
    }

    @Override
    protected void execute() {
       
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: HatchClawClose...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: HatchClawClose...");
    }

}
