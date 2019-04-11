
package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


// This command stops the mecanum drive, and flips the joystick or left thumbstick in Y
public class MecDriveFlipControlStick extends Command {

    public MecDriveFlipControlStick() {
        Logger.setup("Constructing Command: MecDriveFlipControlStick...");

        // Declare subsystem dependencies
        requires(Robot.robotMecDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MecDriveFlipControlStick...");

        Robot.robotMecDriver.stop();
    }

    @Override
    protected void execute() {
        Robot.robotMecDriver.flipControlStickDirection();
    }

    // This command finishes immediately
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDriveFlipControlStick...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: MecDriveFlipControlStick...");
    }

}
