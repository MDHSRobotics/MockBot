
package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.commands.reactive.HatchClawOpen;
import frc.robot.commands.reactive.HatchClawClose;
import frc.robot.consoles.Logger;
import frc.robot.Robot;


// Toggles the position of the Hatcher Claw
public class HatcherToggleClawPosition extends InstantCommand {

    private HatchClawOpen m_hatchClawOpenCmd;
    private HatchClawClose m_hatchClawCloseCmd;

    public HatcherToggleClawPosition() {
        super();
        Logger.setup("Constructing InstantCommand: HatcherToggleClawPosition...");

        m_hatchClawOpenCmd = new HatchClawOpen();
        m_hatchClawCloseCmd = new HatchClawClose();
    }

    @Override
    protected void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: HatcherToggleClawPosition...");

        if (Robot.robotHatcher.clawIsClosed) {
            Logger.action("Hatcher -> Moving to OPEN...");
            m_hatchClawOpenCmd.start();
        }
        else {
            Logger.action("Hatcher -> Moving to CLOSED...");
            m_hatchClawCloseCmd.start();
        }
        Robot.robotHatcher.toggleClawPosition();
    }

}
