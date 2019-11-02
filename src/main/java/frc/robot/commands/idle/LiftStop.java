package frc.robot.commands.idle;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command uses the joystick input to mecanum drive using the cartesian method
public class LiftStop extends Command {

    public LiftStop() {
        Logger.setup("Constructing Command: LiftStop...");

        // Declare subsystem dependencies
        requires(Robot.robotJuanLift);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: LiftStop...");
    }

    @Override
    protected void execute() {
        Robot.robotJuanLift.stop();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: LiftStop...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: LiftStop...");
    }

}
