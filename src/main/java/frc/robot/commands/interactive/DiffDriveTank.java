
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.consoles.Logger;
import frc.robot.helpers.TankMovement;



// This command uses the xbox input to diff drive using the tank drive method
public class DiffDriveTank extends Command {

    public DiffDriveTank() {
        Logger.setup("Constructing Command: TankDrive...");

        // Declare subsystem dependencies
        requires(Robot.robotDiffDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TankDrive...");
    }

    @Override
    protected void execute() {
        TankMovement move = OI.getTankMovementFromThumbsticks(Robot.robotDiffDriver.controlStickDirectionFlipped);
        Robot.robotDiffDriver.driveTank(move.yLeftPosition, move.yRightPosition);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TankDrive...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: TankDrive...");
    }

}
