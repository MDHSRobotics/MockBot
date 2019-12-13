package frc.robot.commands.idle;

import javax.naming.NamingSecurityException;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.Robot;


public class TestLifterStop extends Command {

    public TestLifterStop(){
        Logger.setup("Constructing Command: TestLifterStop... ");
        
        requires(Robot.robotTestLifter);
    }

    @Override
    protected void initialize(){
        Logger.action("Initializing Command: TestLifterStop...");
    }

    @Override
    protected void execute(){
        Robot.robotTestLifter.stop();
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
    
    @Override
    protected void end() {
        Logger.ending("Ending Command: TestLifterStop...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TestLifterStop...");
    }

}


