
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.OI;
import frc.robot.Robot;


// This command uses the joystick input to front wheel drive
public class MecDriveFrontWheel extends Command {

    public MecDriveFrontWheel() {
        Logger.setup("Constructing Command: MecDriveFrontWheel...");

        // Declare subsystem dependencies
        requires(Robot.robotMecDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MecDriveFrontWheel...");
    }

    @Override
    protected void execute() {
        double speed = OI.getFrontWheelDriveSpeed();
        Robot.robotMecDriver.frontWheelDrive(speed);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDriveFrontWheel...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: MecDriveFrontWheel...");
    }

}
