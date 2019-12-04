
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command closes the Hatcher claw via encoder to release, or in preparating to grab, the hatch, and keeps it closed
public class TestPneumaticOpen2 extends Command {

    public TestPneumaticOpen2() {
        Logger.setup("Constructing Command: TestPneumaticOpen...");

        // Declare subsystem dependencies
        requires(Robot.testPneumatic);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TestPneumaticOpen...");

        // Set encoded position
        Robot.testPneumatic.openSolenoid2();
    }

    @Override
    protected void execute() {
        // int position = Robot.robotHatcher.getPosition();
        // int velocity = Robot.robotHatcher.getVelocity();
        // Logger.info("HatchClawClose -> Position: " + position + "; Velocity: " + velocity);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TestPneumaticOpen...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TestPneumaticOpen...");
    }

}
