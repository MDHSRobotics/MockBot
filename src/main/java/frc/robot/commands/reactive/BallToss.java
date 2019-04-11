
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command moves the flipper via encoder to toss the cargo ball into the scoring area, and keeps it there
public class BallToss extends Command {

    public BallToss() {
        Logger.setup("Constructing Command: BallToss...");

        // Declare subsystem dependencies
        requires(Robot.robotBaller);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: BallToss...");

        // Set encoded position
        Robot.robotBaller.tossBall();
    }

    @Override
    protected void execute() {
       // int position = Robot.robotBaller.getPosition();
       // int velocity = Robot.robotBaller.getVelocity();
       // Logger.info("BallToss -> Position: " + position + "; Velocity: " + velocity);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: BallToss...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: BallToss...");
    }


}
