
package frc.robot.commands.test;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


public class LightTestCycle extends Command {

    private static final int NUM_CYCLES = 3;
    private static final int NUM_SECONDS_PER_LIGHT = 1;
    private int m_cycleNum = 1;
    private int m_lightSequence = 0;
    private Timer m_timer;

    public LightTestCycle() {
        Logger.setup("Constructing Command: LightTestCycle...");

        m_timer = new Timer();

        // Declare subsystem dependencies
        requires(Robot.robotLighter);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Logger.action("Initializing Command: LightTestCycle...");

        m_timer.reset();
        m_timer.start();

        // Start off with lights off
        Robot.robotLighter.turnOffBoth();
        Logger.action("Turning off both lights; Cycle #" + m_cycleNum);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double currentTime = m_timer.get();
        if (currentTime > NUM_SECONDS_PER_LIGHT) {
            ++m_lightSequence;
            switch(m_lightSequence) {
                case 1:
                    Robot.robotLighter.turnOnWhiteOnly();
                    Logger.action("Turning on white light; Cycle #" + m_cycleNum);
                    break;
                case 2:
                    Robot.robotLighter.turnOnRedOnly();
                    Logger.action("Turning on red light; Cycle #" + m_cycleNum);
                    break;
                case 3:
                    Robot.robotLighter.turnOnBoth();
                    Logger.action("Turning on both lights; Cycle #" + m_cycleNum);
                    break;
                default:
                    ++m_cycleNum;
                    m_lightSequence = 0;
                    if (m_cycleNum <= NUM_CYCLES) {
                        // If we're not done with all cycles, start a new cycle with both lights off
                        Robot.robotLighter.turnOffBoth();
                        Logger.action("Turning off both lights; Cycle #" + m_cycleNum);
                    }
            }
            m_timer.reset();
            m_timer.start();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return m_cycleNum > NUM_CYCLES;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Logger.ending("Ending Command: LightTestCycle...");

        Robot.robotLighter.turnOffBoth();
        Logger.action("Turning off both lights in end() method");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: LightTestCycle...");

        Robot.robotLighter.turnOffBoth();
        Logger.action("Turning off both lights in interrupted() method");
    }

}
