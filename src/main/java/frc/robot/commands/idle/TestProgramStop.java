
package frc.robot.commands.idle;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command stops the Hatcher motor
public class TestProgramStop extends Command {

    public TestProgramStop() {
        Logger.setup("Constructing Command: TestProgramStop...");

        // Declare subsystem dependencies
        requires(Robot.testProgram);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TestProgramStop...");
    }

    @Override
    protected void execute() {
        Robot.testProgram.stop();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TestProgramStop...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TestProgramStop...");
    }

}
