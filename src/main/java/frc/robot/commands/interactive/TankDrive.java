
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.OI;
import frc.robot.Robot;


// This command uses the joystick input to front wheel drive
public class TankDriveFrontWheel extends Command {

    public TankDriveFrontWheel() {
        Logger.setup("Constructing Command: TankDriveFrontWheel...");

        // Declare subsystem dependencies
        requires(Robot.robo TankDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TankDriveFrontWheel...");
    }

    @Override
    protected void execute() {
        double speed = OI.getFrontWheelDriveSpeed();
        Robot.robo TankDriver.frontWheelDrive(speed);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TankDriveFrontWheel...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TankDriveFrontWheel...");
    }

}