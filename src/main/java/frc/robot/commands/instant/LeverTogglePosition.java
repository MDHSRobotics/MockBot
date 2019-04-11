
package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.commands.reactive.LeverDown;
import frc.robot.commands.reactive.LeverUp;
import frc.robot.consoles.Logger;
import frc.robot.Robot;


// Toggles the position of the Lever
public class LeverTogglePosition extends InstantCommand {

    private LeverDown m_ballHoldCmd;
    private LeverUp m_ballTossCmd;

    public LeverTogglePosition() {
        super();
        Logger.setup("Constructing InstantCommand: LeverTogglePosition...");

        m_ballHoldCmd = new LeverDown();
        m_ballTossCmd = new LeverUp();
    }

    @Override
    protected void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: LeverTogglePosition...");

        if (Robot.robotLever.leverIsUp) {
            Logger.action("Lever -> Moving to DOWN...");
            m_ballHoldCmd.start();
        }
        else {
            Logger.action("Lever -> Moving to UP...");
            m_ballTossCmd.start();
        }
        Robot.robotLever.toggleLeverFlipperPosition();
    }

}
