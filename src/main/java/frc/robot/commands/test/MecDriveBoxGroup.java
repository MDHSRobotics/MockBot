
package frc.robot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.commands.idle.MecDriverRestart;


public class MecDriveBoxGroup extends CommandGroup {

  public MecDriveBoxGroup() {
    addSequential(new MecDriveStraightDistance());
    addSequential(new MecDriverRestart());
    addSequential(new MecDriveRotateAngle(90., 0.1));
    addSequential(new MecDriverRestart());
    addSequential(new MecDriveStraightDistance());
    addSequential(new MecDriverRestart());
    addSequential(new MecDriveRotateAngle(90., 0.1));
    addSequential(new MecDriverRestart());
    addSequential(new MecDriveStraightDistance());
    addSequential(new MecDriverRestart());
    addSequential(new MecDriveRotateAngle(90., 0.1));
    addSequential(new MecDriverRestart());
    addSequential(new MecDriveStraightDistance());
    addSequential(new MecDriverRestart());
    addSequential(new MecDriveRotateAngle(90., 0.1));
    addSequential(new MecDriverRestart());
  }
}
