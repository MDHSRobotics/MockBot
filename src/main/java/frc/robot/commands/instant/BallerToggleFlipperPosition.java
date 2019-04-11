
package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.commands.reactive.BallHold;
import frc.robot.commands.reactive.BallToss;
import frc.robot.consoles.Logger;
import frc.robot.Robot;


// Toggles the position of the Baller's flipper
public class BallerToggleFlipperPosition extends InstantCommand {

    private BallHold m_ballHoldCmd;
    private BallToss m_ballTossCmd;

    public BallerToggleFlipperPosition() {
        super();
        Logger.setup("Constructing InstantCommand: BallerToggleFlipperPosition...");

        m_ballHoldCmd = new BallHold();
        m_ballTossCmd = new BallToss();
    }

    @Override
    protected void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: BallerToggleFlipperPosition...");

        if (Robot.robotBaller.ballIsTossed) {
            Logger.action("Baller -> Moving to HOLD...");
            m_ballHoldCmd.start();
        }
        else {
            Logger.action("Baller -> Moving to TOSS...");
            m_ballTossCmd.start();
        }
        Robot.robotBaller.toggleBallFlipperPosition();
    }

}
