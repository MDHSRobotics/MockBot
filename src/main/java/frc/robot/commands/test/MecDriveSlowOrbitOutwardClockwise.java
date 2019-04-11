
package frc.robot.commands.test;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// Tests the MecDrive slowly orbitting, pointing outward, clockwise
public class MecDriveSlowOrbitOutwardClockwise extends Command {

    public MecDriveSlowOrbitOutwardClockwise() {
        Logger.setup("Constructing Command: MecDriveSlowOrbitOutwardClockwise...");

        // Declare subsystem dependencies
        requires(Robot.robotMecDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MecDriveSlowOrbitOutwardClockwise...");
    }

    @Override
    protected void execute() {
        Robot.robotMecDriver.orbitOutward(1, .5);
    }

    // This command finishes immediately, but is intended to be continually restarted while a button is held
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDriveSlowOrbitOutwardClockwise...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: MecDriveSlowOrbitOutwardClockwise...");

        Robot.robotMecDriver.stop();
    }

}
