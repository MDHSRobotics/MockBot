
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.helpers.ArcadeMovement;
import frc.robot.OI;
import frc.robot.Robot;


// This command uses the joystick input to mecanum drive using the cartesian method
public class OmniDriveArcade extends Command {

    public OmniDriveArcade() {
        Logger.setup("Constructing Command: OmniDriveArcade...");

        // Declare subsystem dependencies
        requires(Robot.robotOmniDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: OmniDriveArcade...");
    }

    @Override
    protected void execute() {
        ArcadeMovement move = OI.getArcadeMovement(Robot.robotOmniDriver.controlStickDirectionFlipped);
        Robot.robotOmniDriver.arcadeDrive(move.speed, move.rotation, false, move.strafe);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: OmniDriveArcade...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: OmniDriveArcade...");
    }

}
