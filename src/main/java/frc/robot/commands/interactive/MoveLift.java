package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.OI;
import frc.robot.Robot;


// This command uses the joystick input to mecanum drive using the cartesian method
public class MoveLift extends Command {

    public MoveLift() {
        Logger.setup("Constructing Command: MoveLift...");

        // Declare subsystem dependencies
        requires(Robot.robotJuanLift);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MoveLift...");
    }

    @Override
    protected void execute() {
        double movePower = OI.getLiftSpeed();
        Robot.robotJuanLift.setMovePower(movePower);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MoveLift...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: MoveLift...");
    }

}
