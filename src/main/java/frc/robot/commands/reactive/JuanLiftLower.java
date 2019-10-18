
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;
import frc.robot.OI;


// This command moves the JuanLiftLower lift system to is min height
public class JuanLiftLower extends Command {

    public JuanLiftLower() {
        Logger.setup("Constructing Command: JuanLiftLower...");

        // Declare subsystem dependencies
        requires(Robot.robotJuanLift);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: JuanLiftLower...");

        // Set encoded position
        Robot.robotJuanLift.lower();
    }

    @Override
    protected void execute() {
       // int position = Robot.robotJuanLift.getPosition();
       // int velocity = Robot.robotJuanLift.getVelocity();
       // Logger.info("JuanLiftLower -> Position: " + position + "; Velocity: " + velocity);

    } 

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: JuanLiftLower...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: JuanLiftLower...");
    }

}




