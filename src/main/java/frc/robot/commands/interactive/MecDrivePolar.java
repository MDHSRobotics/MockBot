
package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.helpers.PolarMovement;
import frc.robot.OI;
import frc.robot.Robot;


// This command uses the joystick input to mecanum drive using the polar method
public class MecDrivePolar extends Command {

    public MecDrivePolar() {
        Logger.setup("Constructing Command: MecDrivePolar...");

        // Declare subsystem dependencies
        requires(Robot.robotMecDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MecDrivePolar...");
    }

    @Override
    protected void execute() {
        PolarMovement move = OI.getPolarMovementFromJoystick(Robot.robotMecDriver.controlStickDirectionFlipped);
        Robot.robotMecDriver.drivePolar(move.magnitude, move.angle, move.rotation);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDrivePolar...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: MecDrivePolar...");
    }

}
