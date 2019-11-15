
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command opens the Hatcher claw via encoder to grab the hatch, and keeps it there
public class TestEncoderBackward extends Command {

    public TestEncoderBackward() {
        Logger.setup("Constructing Command: TestEncoderBackward...");

        // Declare subsystem dependencies
        requires(Robot.testProgram);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TestEncoderBackward...");

        // Set encoded position
        Robot.testProgram.testMotorEncoderBackward();
    }

    @Override
    protected void execute() {
        int position = Robot.testProgram.getPosition();
        int velocity = Robot.testProgram.getVelocity();
        Logger.info("TestEncoderBackward -> Position: " + position + "; Velocity: " + velocity);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TestEncoderBackward...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TestEncoderBackward...");
    }

}
