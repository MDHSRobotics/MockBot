
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command moves the flipper via encoder to toss the cargo ball into the scoring area, and keeps it there
public class LeverUp extends Command {

    public LeverUp() {
        Logger.setup("Constructing Command: LeverUp...");

        // Declare subsystem dependencies
        requires(Robot.robotLever);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: LeverUp...");

        // Set encoded position
        Robot.robotLever.upLever();
    }

    @Override
    protected void execute() {
        int position = Robot.robotLever.getPosition();
        int velocity = Robot.robotLever.getVelocity();
        Logger.info("LeverUp -> Position: " + position + "; Velocity: " + velocity);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: LeverUp...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: LeverUp...");
    }


}
