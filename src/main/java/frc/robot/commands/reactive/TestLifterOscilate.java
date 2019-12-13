
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command lift it up and down using limit switch
public class TestLifterOscilate extends Command {

    public TestLifterOscilate() {
        Logger.setup("Constructing Command: TestLifterOscilate...");

        // Declare subsystem dependencies
        requires(Robot.robotTestLifter);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TestLifterOscilate...");
    }

    @Override
    protected void execute() {
        // Set encoded position
        boolean isGoingUp = Robot.robotTestLifter.goingUp;

        if (isGoingUp) {
            boolean hitUpLimitSwitch = Robot.robotTestLifter.isUpLimitSwitchPushed();
            if (hitUpLimitSwitch) {
                Robot.robotTestLifter.goingUp = false;
                Robot.robotTestLifter.liftDown();
            }
            else {
                Robot.robotTestLifter.liftUp();
            }
        }
        else {
            boolean hitDownLimitSwitch = Robot.robotTestLifter.isUpLimitSwitchPushed();
            if (hitDownLimitSwitch) {
                Robot.robotTestLifter.goingUp = true;
                Robot.robotTestLifter.liftUp();
            }
            else {
                Robot.robotTestLifter.liftDown();
            }
        }
    }
        
    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TestLifterOscilate...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TestLifterOscilate...");
    }


}
