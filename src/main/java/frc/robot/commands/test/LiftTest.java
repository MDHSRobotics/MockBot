package frc.robot.commands.test;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.helpers.CartesianMovement;
import frc.robot.Devices;
import frc.robot.OI;
import frc.robot.Robot;


// This command uses the joystick input to mecanum drive using the cartesian method
public class LiftTest extends Command {

    public LiftTest() {
        Logger.setup("Constructing Command: LiftTest...");

        // Declare subsystem dependencies
        requires(Robot.robotJuanLift);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: LiftTest...");
    }

    @Override
    protected void execute() {
        Robot.robotJuanLift.testMotor();;
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: LiftTest...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: LiftTest...");
    }

}
