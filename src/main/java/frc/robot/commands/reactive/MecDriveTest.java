
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Devices;
import frc.robot.Robot;


// This command stops the MecDriver
public class MecDriveTest extends Command {

    public MecDriveTest() {
        Logger.setup("Constructing Command: MecDriverRestart...");

        // Declare subsystem dependencies
        requires(Robot.robotMecDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MecDriverRestart...");
    }

    @Override
    protected void execute() {
        Devices.talonSrxMecWheelFrontRight.set(.10);
        Devices.talonSrxMecWheelFrontLeft.set(.10);
        Devices.talonSrxMecWheelRearLeft.set(.10);
        Devices.talonSrxMecWheelRearRight.set(.10);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDriverRestart...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: MecDriverRestart...");
    }

}
