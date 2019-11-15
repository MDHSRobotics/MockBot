
package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.commands.reactive.TestEncoderForward;
import frc.robot.commands.reactive.TestEncoderBackward;
import frc.robot.consoles.Logger;
import frc.robot.Robot;


// Toggles the position of the Hatcher Claw
public class TestProgramToggleEncoderRotations extends InstantCommand {

    private TestEncoderForward m_testEncoderForwardCmd;
    private TestEncoderBackward m_testEncoderBackwardCmd;

    public TestProgramToggleEncoderRotations() {
        super();
        Logger.setup("Constructing InstantCommand: TestProgramToggleEncoderRotations...");

        m_testEncoderForwardCmd = new TestEncoderForward();
        m_testEncoderBackwardCmd = new TestEncoderBackward();
    }

    @Override
    protected void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: TestProgramToggleEncoderRotations...");

        if (Robot.testProgram.rotationIsBackward) {
            Logger.action("TestProgram -> Moving to FORWARD...");
            m_testEncoderForwardCmd.start();
        }
        else {
            Logger.action("TestProgram -> Moving to BACKWARD...");
            m_testEncoderBackwardCmd.start();
        }
        Robot.testProgram.togglePosition();
    }

}
