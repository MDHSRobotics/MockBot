
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command closes the Hatcher claw via encoder to release, or in preparating to grab, the hatch, and keeps it closed
public class HatchClawClose extends Command {

    public HatchClawClose() {
        Logger.setup("Constructing Command: HatchClawClose...");

        // Declare subsystem dependencies
        requires(Robot.robotHatcher);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: HatchClawClose...");

        // Set encoded position
        Robot.robotHatcher.closeClaw();
    }

    @Override
    protected void execute() {
        // int position = Robot.robotHatcher.getPosition();
        // int velocity = Robot.robotHatcher.getVelocity();
        // Logger.info("HatchClawClose -> Position: " + position + "; Velocity: " + velocity);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
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
