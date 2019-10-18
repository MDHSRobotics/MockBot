
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;
import frc.robot.OI;


// This command moves the JuanLiftRaise lift system to is max height
public class JuanLiftRaise extends Command {

    public JuanLiftRaise() {
        Logger.setup("Constructing Command: JuanLiftRaise...");

        // Declare subsystem dependencies
        requires(Robot.robotJuanLift);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: JuanLiftRaise...");

        // Set encoded position
        Robot.robotJuanLift.raise();
    }

    @Override
    protected void execute() {
       // int position = Robot.robotJuanLift.getPosition();
       // int velocity = Robot.robotJuanLift.getVelocity();
       // Logger.info("JuanLiftRaise -> Position: " + position + "; Velocity: " + velocity);

    } 

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: JuanLiftRaise...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: JuanLiftRaise...");
    }

}




