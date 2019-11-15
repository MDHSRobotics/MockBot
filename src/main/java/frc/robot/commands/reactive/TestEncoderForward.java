
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command closes the Hatcher claw via encoder to release, or in preparating to grab, the hatch, and keeps it closed
public class TestEncoderForward extends Command {

    public TestEncoderForward() {
        Logger.setup("Constructing Command: TestEncoderForward...");

        // Declare subsystem dependencies
        requires(Robot.testProgram);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TestEncoderForward...");

        // Set encoded position
        Robot.testProgram.testMotorEncoderForward();
    }

    @Override
    protected void execute() {
        int position = Robot.testProgram.getPosition();
        int velocity = Robot.testProgram.getVelocity();
        Logger.info("TestEncoderForward -> Position: " + position + "; Velocity: " + velocity);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TestEncoderForward...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TestEncoderForward...");
    }

}
