
package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.consoles.Logger;
import frc.robot.Robot;
import frc.robot.commands.interactive.MecDriveFrontWheel;


// This command sets the Game Mode to Climb
public class RobotGameModeClimb extends InstantCommand {

    private MecDriveFrontWheel m_mecDrivePlatFormCmd;

    public RobotGameModeClimb() {
        super();
        Logger.setup("Constructing InstantCommand: RobotGameModeClimb...");
        m_mecDrivePlatFormCmd = new MecDriveFrontWheel();
    }

    @Override
    protected void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: RobotGameModeClimb...");

        if (Robot.robotGameMode == Robot.GameMode.CLIMB) {
            if (Robot.robotClimbMode == Robot.ClimbMode.HAB2) {
                Robot.robotClimbMode = Robot.ClimbMode.HAB3;
                Logger.info("Robot Climb Mode is now HAB3");
            }
            else if (Robot.robotClimbMode == Robot.ClimbMode.HAB3){
                Robot.robotClimbMode = Robot.ClimbMode.HAB2;
                Logger.info("Robot Climb Mode is now HAB2");
            }
        }
        else {
            Robot.robotGameMode = Robot.GameMode.CLIMB;
            Logger.info("Robot Game Mode is now CLIMB; Climb Mode is " + Robot.robotClimbMode);
            m_mecDrivePlatFormCmd.start();
        }
    }

}
