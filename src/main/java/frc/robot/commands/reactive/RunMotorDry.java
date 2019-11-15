
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command opens the Hatcher claw via encoder to grab the hatch, and keeps it there
public class RunMotorDry extends Command {

    public () {
        Logger.setup("Constructing Command: RunMotorDry...");

        // Declare subsystem dependencies
        requires(Robot.testProgram);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: RunMotorDry...");

    }

    @Override
    protected void execute() {
        Robot.testProgram.testMotor();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: RunMotorDry...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: RunMotorDry...");
    }

}
