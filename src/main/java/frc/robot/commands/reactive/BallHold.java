
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;
import frc.robot.OI;


// This command moves the Baller to hold the cargo ball via encoder, and keeps it there until it is ready to be tossed
public class BallHold extends Command {
    private BallToss m_BallTossCmd;

    public BallHold() {
        Logger.setup("Constructing Command: BallHold...");

        // Declare subsystem dependencies
        requires(Robot.robotBaller);
        m_BallTossCmd = new BallToss();
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: BallHold...");

        // Set encoded position
        Robot.robotBaller.holdBall();
    }

    @Override
    protected void execute() {
       // int position = Robot.robotBaller.getPosition();
       // int velocity = Robot.robotBaller.getVelocity();
       // Logger.info("BallHold -> Position: " + position + "; Velocity: " + velocity);

       double speedToss = OI.getBallSpeed();
       if (speedToss != 0) {
           m_BallTossCmd.start();
       }

    } 

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: BallHold...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: BallHold...");
    }

}




