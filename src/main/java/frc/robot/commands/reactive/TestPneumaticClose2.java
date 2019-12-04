
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command opens the Hatcher claw via encoder to grab the hatch, and keeps it there
public class TestPneumaticClose2 extends Command {

    public TestPneumaticClose2() {
        Logger.setup("Constructing Command: TestPneumaticClose...");

        // Declare subsystem dependencies
        requires(Robot.testPneumatic);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TestPneumaticClose...");

        // Set encoded position
        Robot.testPneumatic.closeSolenoid2();
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
        Logger.ending("Ending Command: TestPneumaticClose...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TestPneumaticClose...");
    }

}
