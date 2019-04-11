
package frc.robot.commands.reactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.sensors.Distance;
import frc.robot.sensors.Vision;
import frc.robot.OI;
import frc.robot.Robot;


// This command toggles the "Lighter" lights from certain sensor states
public class LightToggle extends Command {

    public LightToggle() {
        Logger.setup("Constructing Command: LightToggle...");

        // Declare subsystem dependencies
        requires(Robot.robotLighter);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: LightToggle...");
    }

    @Override
    protected void execute() {
        boolean frontLineDetected = Vision.frontLineDetected();
        boolean rightLineDetected = Vision.rightLineDetected();
        boolean leftLineDetected = Vision.leftLineDetected();
        if (frontLineDetected || leftLineDetected || rightLineDetected) {
            int dpadAngle = OI.getDpadAngleForGyro();
            boolean isAligned = Robot.robotMecDriver.isAligned(dpadAngle);
            if (isAligned) {
                boolean closeEnough = Distance.distanceReached();
                if (closeEnough) {
                    Robot.robotLighter.turnOnBoth();
                }
                else {
                    Robot.robotLighter.turnOnRedOnly();
                }
            }
            else {
                Robot.robotLighter.turnOnWhiteOnly();
            }
        }
        else {
            Robot.robotLighter.turnOffBoth();
        }
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: LightToggle...");

        Robot.robotLighter.turnOffBoth();
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: LightToggle...");

        Robot.robotLighter.turnOffBoth();
    }

}
