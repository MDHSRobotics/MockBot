<<<<<<< HEAD

package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.OI;
import frc.robot.Robot;


// This command uses the joystick input to front wheel drive
public class TankDriveFrontWheel extends Command {

    public TankDriveFrontWheel() {
        Logger.setup("Constructing Command: TankDriveFrontWheel...");

        // Declare subsystem dependencies
        requires(Robot.robo TankDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: TankDriveFrontWheel...");
    }

    @Override
    protected void execute() {
        double speed = OI.getFrontWheelDriveSpeed();
        Robot.robo TankDriver.frontWheelDrive(speed);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: TankDriveFrontWheel...");
    }

    @Override
    protected void interrupted() {
        Logger.ending("Interrupting Command: TankDriveFrontWheel...");
    }

}
=======

package frc.robot.commands.interactive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.helpers.CartesianMovement;
import frc.robot.subsystems.TankDriver;
import frc.robot.OI;
import frc.robot.Robot;


// This command uses the joystick input to mecanum drive using the cartesian method
public class TankDrive extends Command {

    public TankDrive() {
        Logger.setup("Constructing Command: MecDriveCartesian...");

        // Declare subsystem dependencies
        requires(Robot.robotTankDriver);
    }

    @Override
    protected void initialize() {
        Logger.action("Initializing Command: MecDriveCartesian...");
    }

    @Override
    protected void execute() {
        CartesianMovement move = OI.getCartesianMovement(Robot.robotMecDriver.controlStickDirectionFlipped);
        Robot.robotMecDriver.driveCartesian(move.ySpeed, move.xSpeed, move.zRotation);
    }

    // This command continues until interrupted
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Logger.ending("Ending Command: MecDriveCartesian...");
    }

    @Override
    protected void interrupted() {
        System.out.println("--");
        Logger.ending("Interrupting Command: MecDriveCartesian...");
    }

}
>>>>>>> tank
