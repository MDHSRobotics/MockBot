
package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.consoles.Logger;
import frc.robot.Robot;
import frc.robot.commands.interactive.MecDriveCartesian;


// This command sets the Game Mode to DELIVERY
public class RobotGameModeDelivery extends InstantCommand {
    
    private MecDriveCartesian m_mecDriveCartesianCmd;

    public RobotGameModeDelivery() {
        super();
        Logger.setup("Constructing InstantCommand: RobotGameModeDelivery...");
        m_mecDriveCartesianCmd = new MecDriveCartesian();
    }

    @Override
    protected void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: RobotGameModeDelivery...");

        if (Robot.robotGameMode == Robot.GameMode.DELIVERY) {
            Logger.info("Robot Game Mode is still DELIVERY");
        }
        else {
            Logger.info("Robot Game Mode is now DELIVERY");
            Robot.robotGameMode = Robot.GameMode.DELIVERY;
            m_mecDriveCartesianCmd.start();
        }
    }

}
