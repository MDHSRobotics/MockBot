
package frc.robot.commands.test;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// Tests the MecDrive slowly driving forward
public class MecDriveSlowForward extends Command {

    public MecDriveSlowForward() {
        Logger.setup("Constructing Command: MecDriveSlowForward...");

        // Declare subsystem dependencies
        requires(Robot.robotMecDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MecDriveSlowForward...");
    }

    @Override
    protected void execute() {
        Robot.robotMecDriver.driveStraight(.5);
    }

    // This command finishes immediately, but is intended to be continually restarted while a button is held
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDriveSlowForward...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: MecDriveSlowForward...");

        Robot.robotMecDriver.stop();
    }

}
