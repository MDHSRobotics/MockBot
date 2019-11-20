
package frc.robot.commands.idle;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command stops the Hatcher motor
public class TestPneumaticStop extends Command {

    public TestPneumaticStop() {
        Logger.setup("Constructing Command: TestPneumaticStop...");

        // Declare subsystem dependencies
        requires(Robot.testPneumatic);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TestPneumaticStop...");
    }

    @Override
    protected void execute() {
        Robot.testPneumatic.stop();
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TestPneumaticStop...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TestPneumaticStop...");
    }

}
