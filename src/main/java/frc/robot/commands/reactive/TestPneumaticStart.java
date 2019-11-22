
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command opens the Hatcher claw via encoder to grab the hatch, and keeps it there
public class TestPneumaticStart extends Command {

    public TestPneumaticStart() {
        Logger.setup("Constructing Command: TestPneumaticStart...");

        // Declare subsystem dependencies
        requires(Robot.testPneumatic);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TestPneumaticStart...");

        // Set encoded position
        Robot.testPneumatic.start();
    }

    @Override
    protected void execute() {
        // int position = Robot.robotHatcher.getPosition();
        // int velocity = Robot.robotHatcher.getVelocity();
        // Logger.info("HatchClawOpen -> Position: " + position + "; Velocity: " + velocity);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TestPneumaticStart...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TestPneumaticStart...");
    }

}
