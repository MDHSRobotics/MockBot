
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command opens the Hatcher claw via encoder to grab the hatch, and keeps it there
public class TestMotorDry extends Command {

    public TestMotorDry() {
        Logger.setup("Constructing Command: TestMotorDry...");

        // Declare subsystem dependencies
        requires(Robot.testProgram);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TestMotorDry...");

    }

    @Override
    protected void execute() {
        Robot.testProgram.testMotorDry();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TestMotorDry...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TestMotorDry...");
    }

}
