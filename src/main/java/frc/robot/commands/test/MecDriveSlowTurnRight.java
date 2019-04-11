
package frc.robot.commands.test;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// Tests the MecDrive slowly turning right
public class MecDriveSlowTurnRight extends Command {

    public MecDriveSlowTurnRight() {
        Logger.setup("Constructing Command: MecDriveSlowTurnRight...");

        // Declare subsystem dependencies
        requires(Robot.robotMecDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MecDriveSlowTurnRight...");
    }

    @Override
    protected void execute() {
        Robot.robotMecDriver.rotate(.5);
    }

    // This command finishes immediately, but is intended to be continually restarted while a button is held
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDriveSlowTurnRight...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: MecDriveSlowTurnRight...");

        Robot.robotMecDriver.stop();
    }

}
